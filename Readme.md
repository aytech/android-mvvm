# Example of building an app using the MVVM (Model View ViewModel) architecture pattern

In MVVM, the app consists of the following components:

* **_ViewModel_**. Receives data from Model, applies logic and exposes data to Views. Although not
an Activity, ViewModel can manage data in lifecycle-aware manner. No references to Android classes.
* **_Model_**. Contains data only, which is exposed to ViewModels. Can also receive events from
ViewModels to manipulate data. No references to Android classes.
* **_View_**. This is the Android Activity or a Fragment. Can observe one or more ViewModels and
update UI based on the data received. Informs ViewModel of user actions.

## _MVVM flow_

1. **View** accepts user input and passes it to **ViewModel**.
2. **ViewModel** updates data in **Model**.
3. **Model** notifies **ViewModel** after the data is updated.
4. **ViewModel** broadcasts data update events, provides data.
5. **View** is observing for data change events, updates UI.