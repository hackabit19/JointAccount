import pandas as pd
from flask import Flask, jsonify, request
import pickle

# load model
model = pickle.load(open('model_music.pkl','rb'))

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
    cols = ['relative_time', 'gender', 'age', 'curr_temprature', 'curr_mood_1',
           'curr_mood_2', 'curr_mood_3', 'curr_mood_4', 'curr_mood_5',
           'curr_mood_6', 'curr_mood_7', 'curr_mood_8', 'weather_1', 'weather_2',
           'weather_3', 'weather_4', 'chronic_1', 'chronic_2', 'chronic_3',
           'chronic_4', 'chronic_5', 'chronic_6', 'chronic_7', 'employed',
           'student', 'artist_1', 'artist_2', 'artist_3', 'artist_4', 'artist_5',
           'artist_6', 'artist_7', 'artist_8', 'artist_9', 'artist_10',
           'artist_11', 'artist_12', 'artist_13', 'artist_14', 'artist_15',
           'lang_1', 'lang_2', 'lang_3', 'genre_1', 'genre_2', 'genre_3',
           'genre_4', 'genre_5', 'genre_6', 'genre_7', 'genre_8', 'mus_decade_1',
           'mus_decade_2', 'mus_decade_3', 'mus_decade_4', 'mus_decade_5',
           'mus_decade_6']
    
    X = data_df[cols] 
    
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