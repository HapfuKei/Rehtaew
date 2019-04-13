import pandas
from sklearn import preprocessing
from sklearn.ensemble import RandomForestRegressor

data = pandas.read_csv("data/history_export.csv")
data.reset_index()
# add Next day temp
data.loc[:, "PrevTemp"] = data["Temperature  [2 m above gnd]"].shift(-1)
data.loc[data.shape[0] - 1, "PrevTemp"] = data["Temperature  [2 m above gnd]"][data.shape[0] - 1]

# standardize the data attributes
normalize_data = preprocessing.normalize(data)
standardized_data = preprocessing.scale(normalize_data)

temperatureLevel = [-30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11,
                    -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                    17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
                    27, 28, 29, 30]
# make ML model
for i in range(0, data.shape[0] - 1):
    data.loc[i, "PrevTemp"] = round(data.loc[i, "PrevTemp"])
    if data.loc[i, "PrevTemp"] == -0:
        data.loc[i, "PrevTemp"] = 0
data.loc[data.shape[0] - 1, "PrevTemp"] = round(data.loc[data.shape[0] - 1, "PrevTemp"])





print(data)
print("DONE")
