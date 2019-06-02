# Import libraries
import json

import pandas as pd
from flask import Flask, request
from sklearn import preprocessing
from sklearn.externals import joblib

app = Flask(__name__)

# Load the model
model = joblib.load("rehtaewModel.pkl")


@app.route('/api', methods=['POST'])
def predict():
    # Get the data from the POST request.
    data = request.form['testData']
    data = json.loads(data)
    dataFrame = pd.DataFrame(columns=['Year',
                                      'Month',
                                      'Day',
                                      'Hour',
                                      'Temperature',
                                      'Relative Humidity',
                                      'Sea Level Pressure',
                                      'Total Precipitation (high resolution)',
                                      'Total Precipitation (low resolution)',
                                      'Snowfall Amount (high resolution)',
                                      'Snowfall Amount (low resolution)',
                                      'Total Cloud Cover',
                                      'High Cloud Cover',
                                      'Medium Cloud Cover',
                                      'Low Cloud Cover',
                                      'Sunshine Duration',
                                      'Shortwave Radiation',
                                      'Wind Speed 10m',
                                      'Wind Direction 10m',
                                      'Wind Speed 80m',
                                      'Wind Direction 80m',
                                      'Wind Speed 900mb',
                                      'Wind Direction 900mb',
                                      'Wind Gust'])
    gettedData = {
        "Year": data['Year'],
        "Month": data['Month'],
        "Day": data['Day'],
        "Hour": data['Hour'],
        "Temperature": data['Temperature'],
        "Relative Humidity": data['Relative_Humidity'],
        "Sea Level Pressure": data['Sea_Level_Pressure'],
        "Total Precipitation (high resolution)": data['Total_Precipitation_high'],
        "Total Precipitation (low resolution)": data['Total_Precipitation_low'],
        "Snowfall Amount (high resolution)": data['Snowfall_Amount_high'],
        "Snowfall Amount (low resolution)": data['Snowfall_Amount_low'],
        "Total Cloud Cover": data['Total_Cloud_Cover'],
        "High Cloud Cover": data['High_Cloud_Cover'],
        "Medium Cloud Cover": data['Medium_Cloud_Cover'],
        "Low Cloud Cover": data['Low_Cloud_Cover'],
        "Sunshine Duration": data['Sunshine_Duration'],
        "Shortwave Radiation": data['Shortwave_Radiation'],
        "Wind Speed 10m": data['Wind_Speed_10m'],
        "Wind Direction 10m": data['Wind_Direction_10m'],
        "Wind Speed 80m": data['Wind_Speed_80m'],
        "Wind Direction 80m": data['Wind_Direction_80m'],
        "Wind Speed 900mb": data['Wind_Speed_900mb'],
        "Wind Direction 900mb": data['Wind_Direction_900mb'],
        "Wind Gust": data['Wind_Gust']
    }
    dataFrame.loc[0] = gettedData

    dataFrame = preprocessing.normalize(dataFrame)
    dataFrame = preprocessing.scale(dataFrame)

    prediction = model.predict(dataFrame)
    # Take the first value of prediction
    output = prediction[0]
    print("model output=" + str(output))

    return str(output), 200, {'Content-Type': 'text/plain; charset=utf-8,Access-Control-Allow-Origin',
                              'Access-Control-Allow-Origin': '*'}


if __name__ == '__main__':
    app.run(port=5000, debug=True)
