# What is Data Binding?
**Data Binding** is a library in Android that facilitates a connection between UI components and data sources. Instead of manually setting values in views (like `TextViews`) by finfViewById() from your code, you can define these relationships directly in your XML files, making your code more concise and maintainable.

### Benefits:
- Direct binding between UI components and data sources.
- Reduces boilerplate code (e.g., no need for `findViewById()`).
- Supports two-way binding (e.g., user inputs).
- Easily integrates with `LiveData` and `ViewModel`.

# What is ViewModel?

**ViewModel** is a class in Android that is designed to manage **UI-related data** in a lifecycle-conscious way. It allows data to survive configuration changes such as screen rotations or Activity/Fragment recreation. ViewModel ensures that the **UI data** is retained even if the lifecycle of the Activity or Fragment changes.

### Benefits:
- **Prevents Data Loss:** Data is preserved across configuration changes, such as when the UI component is recreated.
- **Cleaner Code:** Separates UI logic from data management, leading to cleaner and more organized code.
- **Works with LiveData:** ViewModel works seamlessly with LiveData to automatically reflect data changes in the UI.


