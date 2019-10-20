import pandas as pd
from flask import Flask, jsonify, request
import pickle

# load model
model = pickle.load(open('model_place.pkl','rb'))

# app
app = Flask(__name__)

# routes
@app.route('/', methods=['POST'])

def predict():
    # get data
    data = request.get_json(force=True)
    print(data)
    # convert data into dataframe
    data_df = pd.DataFrame.from_dict([data])
    
    # predictions
    cols = ['salary', 'expenditure_pm', 'gender', 'age', 'curr_temprature','curr_mood_1', 'curr_mood_2', 'curr_mood_3', 'curr_mood_4','curr_mood_5', 'curr_mood_6', 'curr_mood_7', 'curr_mood_8', 'weather_1','weather_2', 'weather_3', 'weather_4','allergy_1', 'allergy_2','allergy_3', 'allergy_4', 'allergy_5', 'chronic_1', 'chronic_2','chronic_3', 'chronic_4','chronic_5', 'chronic_6', 'chronic_7','employed', 'student', 'ind_cuisine_1', 'ind_cuisine_2','ind_cuisine_3', 'ind_cuisine_4', 'hobby_1', 'hobby_2', 'hobby_3','hobby_4', 'hobby_5', 'hobby_6', 'hobby_7', 'hobby_8', 'hobby_9','hobby_10']
    
    X = data_df[cols] 
    
    val = X['salary'].values[0]
    if val == 0:
        ex_data = {'salary_0':[1],'salary_1':[0],'salary_2':[0],'salary_3':[0]}
    elif val == 1:
        ex_data = {'salary_0':[0],'salary_1':[1],'salary_2':[0],'salary_3':[0]}
    elif val == 2:
        ex_data = {'salary_0':[0],'salary_1':[0],'salary_2':[1],'salary_3':[0]}
    elif val == 3:
        ex_data = {'salary_0':[0],'salary_1':[0],'salary_2':[0],'salary_3':[1]}
    
    X = pd.concat([X,pd.DataFrame(data=ex_data)],axis=1)
    
    X.drop(['salary'],axis=1, inplace=True)
    
    val = X['expenditure_pm'].values[0]
    if val == 0:
        ex_data = {'expenditure_pm_0':[1],'expenditure_pm_1':[0],'expenditure_pm_2':[0],'expenditure_pm_3':[0],
                   'expenditure_pm_4':[0]}
    elif val == 1:
        ex_data = {'expenditure_pm_0':[0],'expenditure_pm_1':[1],'expenditure_pm_2':[0],'expenditure_pm_3':[0],
                  'expenditure_pm_4':[0]}
    elif val == 2:
        ex_data = {'expenditure_pm_0':[0],'expenditure_pm_1':[0],'expenditure_pm_2':[1],'expenditure_pm_3':[0],
                  'expenditure_pm_4':[0]}
    elif val == 3:
        ex_data = {'expenditure_pm_0':[0],'expenditure_pm_1':[0],'expenditure_pm_2':[0],'expenditure_pm_3':[1],
                  'expenditure_pm_4':[0]}
    elif val == 4:
        ex_data = {'expenditure_pm_0':[0],'expenditure_pm_1':[0],'expenditure_pm_2':[0],'expenditure_pm_3':[0],
                  'expenditure_pm_4':[1]}
    
    X = pd.concat([X,pd.DataFrame(data=ex_data)],axis=1)
    
    X.drop(['expenditure_pm'],axis=1, inplace=True)
    
    result = model.predict(X.values.reshape(1,-1))
    result_proba = model.predict_proba(X.values.reshape(1,-1))
    
    # send back to browser
    output = {
              'max': int(result[0]),
              'prob': result_proba.tolist()
             }

    # return data
    return jsonify(results=output)

if __name__ == '__main__':
    app.run(port = 5000, debug=True)