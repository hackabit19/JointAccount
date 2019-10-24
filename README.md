# DecisiO

Welcome Fellas to our project DecisiO !

AIM OF PROJECT :- To provide a personal suggestor in form of an android app. This app will intially have capabilities of suggesting 5 things, namely Food, Cloth, Activity to do, Music and Place to visit.

TECH STACK USED :- Python, Android, Machine Learning, Flask API, Heroku for deployment.

WORK FLOW :- Firstly, the features for the above mentioned 5 queries were decided. Features includes Salary Range, Age, Per Month Expenses, Gender, etc. A total of 102 features were written. Then with the help of Data Generation techniques, dataset of 5000 units was made. Then, various types of Machine Learning Algorithms were used. Following accuracies were obtained :-

    1. Random Forest Classifier -> 70.4 %
    2. Decision Tree Analysis ->  65.2 %
    3. Logistic Regression -> 40 %
    4. Support Vector Machines (SVM) -> 44.5 %
    5. K Nearest Neighbours -> 71.4 %
    
Seperate models were made for each query, i.e. 5 different models were trained for 5 queries. Each of them is hosted on Heroku.
Then, using Post requests and Flask API, the model weights and data were updated continously. The android app contains questions for the user. He/She will be asked for a frequency for questions to be asked regularly at the start of the app. After that, he will have 2 options either to ask a question or answer a question.
