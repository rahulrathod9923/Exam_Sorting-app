This a Spring boot module for getting sorted examination. As well as there is integration of Html, Css and JavaScript for HTTP request and for UI. 

EXPLAINATION:
There is four fields in Entity corrospondingly- ExamName, StartDate, EndDate and Retro/extendedDate.
In service layer there is algorithm for sort the exams, Like:
if and only if and only if there endDate of any examination is crossed todays date then sort by retroDate/extendedDate
otherwise sort by endDate, then if both conditions are not satisfying then sort by StartDate otherwise sort by ExamName.charAt[0].

Note: only that exam should appair on top which's RetroDate/EndDate/StartDate is going to end.
