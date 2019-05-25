import pandas
from sklearn import preprocessing, metrics
from sklearn.externals import joblib
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier

data = pandas.read_csv("data/history_export.csv")
data.reset_index()
# add Next day temp
data.loc[:, "NextTemp"] = data["Temperature  [2 m above gnd]"].shift(-1)
data.loc[data.shape[0] - 1, "NextTemp"] = data["Temperature  [2 m above gnd]"][data.shape[0] - 1]

# check Nan
print(pandas.np.isnan(data))
# standardize the data attributes
normalize_data = preprocessing.normalize(data)
standardized_data = preprocessing.scale(normalize_data)

temperatureLevel = [-30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11,
                    -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                    17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
                    27, 28, 29, 30]
# make ML model
for i in range(0, data.shape[0] - 1):
    data.loc[i, "NextTemp"] = round(data.loc[i, "NextTemp"])
    if data.loc[i, "NextTemp"] == -0:
        data.loc[i, "NextTemp"] = 0
data.loc[data.shape[0] - 1, "NextTemp"] = round(data.loc[data.shape[0] - 1, "NextTemp"])

features = []
labels = []

# for i in range(0,data.shape[0]-1):
#     features.append()
#     labels.append(data[])

features = data.loc[:, "Year":"Wind Gust  [sfc]"]
labels = data.loc[:, "NextTemp"]

features = preprocessing.normalize(features)
features = preprocessing.scale(features)

# Split the dataset on training and testing sets
features_train, features_test, labels_train, labels_test = train_test_split(features, labels, test_size=0.2,
                                                                            random_state=0, shuffle=True)

# Setting up vectorizer that will convert dataset into vectors using n-gram
# model = LogisticRegression() 57
# model = KNeighborsClassifier() 99
model = DecisionTreeClassifier(max_features=24)

# Pass training set of features and labels though pipe.
model.fit(features_train, labels_train)

# Test model accuracy by running feature test set
labels_predicted = model.predict(features_test)

print(metrics.classification_report(labels_test, labels_predicted))
score = metrics.accuracy_score(labels_test, labels_predicted)
print("accuracy is = "+score.__str__())

joblib.dump(model, 'rehtaewModel.pkl')

print("DONE")
