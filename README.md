Readme
==============
Kevin Tang
tkevin-Todo
CMPUT 301 Assignment 1 - Todo List
The assignment involves creating an android application in java used for storing and archiving of todo items on a todo list. Items may be checked off, emailed, and deleted.

This application involves using the Contextual Action Bar (CAB) for selecting items. The process involves holding a long click on an item which will overlay a menu on the top. Delete, archive, and email are available from the CAB. The summary and archive page is accessible through the regular menu on the top right. This menu also includes functions for bulk email.

The archive menu uses a similar CAB but with an unarchive option. 

Creation of todo items are made through the textbox on the bottom of the main screen.The data is then saved using shared preferences.

Known Issues
==============
- Does not properly display items that are over a line long.
- Email works?

References
==============

1. Abram Hindle's Student Picker tutorials and the code within was adapted for learning basic Android OO implementation and for saving data via shared preferences and serialization.
2. Vogella's tutorials were adapted for use of the contextual action bar for the listview (http://www.vogella.com/tutorials/AndroidListView/article.html) (http://www.vogella.com/code/de.vogella.android.listactivity/src/de/vogella/android/listactivity/MyListActivityActionbar.html),
3. Custom Adapter was stylizied after SurvivingWithAndroid's planet custom listview tutorial (https://github.com/survivingwithandroid/Surviving-with-android/tree/master/SimpleList).

