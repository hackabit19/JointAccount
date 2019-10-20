import pandas as pd
from flask import Flask, jsonify, request
import pickle

# load model
model = pickle.load(open('model_cloth.pkl','rb'))

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
    cols = ['salary', 'expenditure_pm', 'relative_time', 'gender', 'age',
           'curr_temprature', 'curr_mood_1', 'curr_mood_2', 'curr_mood_3',
           'curr_mood_4', 'curr_mood_5', 'curr_mood_6', 'curr_mood_7',
           'curr_mood_8', 'weather_1', 'weather_2', 'weather_3', 'weather_4',
           'employed', 'student', 'artist_1', 'artist_2', 'artist_3', 'artist_4',
           'artist_5', 'artist_6', 'artist_7', 'artist_8', 'artist_9', 'artist_10',
           'artist_11', 'artist_12', 'artist_13', 'artist_14', 'artist_15',
           'clt_mat_1', 'clt_mat_2', 'clt_mat_3', 'clt_mat_4', 'clt_mat_5',
           'color_1', 'color_2', 'color_3', 'color_4', 'color_5', 'color_6',
           'color_7', 'color_8', 'color_9', 'color_10']
    
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
    
    val = X['relative_time'].values[0]
    if val == 0:
        ex_data = {'relative_time_0':[1],'relative_time_1':[0],'relative_time_2':[0],'relative_time_3':[0]}
    elif val == 1:
        ex_data = {'relative_time_0':[0],'relative_time_1':[1],'relative_time_2':[0],'relative_time_3':[0]}
    elif val == 2:
        ex_data = {'relative_time_0':[0],'relative_time_1':[0],'relative_time_2':[1],'relative_time_3':[0]}
    elif val == 3:
        ex_data = {'relative_time_0':[0],'relative_time_1':[0],'relative_time_2':[0],'relative_time_3':[1]}
    
    X = pd.concat([X,pd.DataFrame(data=ex_data)],axis=1)
    
    X.drop(['relative_time'],axis=1, inplace=True)
    
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