
/*
A package-info is not actually a real java class -- there's only one line of "code" -- a package declaration
that matches the folder you find it in. However, it gives us a space to create a JavaDoc comment with a slash
and double star, to describe the whole folder. We can even use @link in our description to help us refer to
other packages or classes!
 */





/**
 * 
 * This package contains all the classes whose job it is to 'represent' a real-world object.
 * Model classes have behaviors related to calculating salary or otherwise solving tasks related
 * to their individual values, but they should usually not be created directly in main.
 * 
 * Instead, they will be loaded by an appropriate loader from {@link data.loader}. When you want to
 * search through multiple objects of a model, use {@link util.PayrollSearcher}.
 */
package data.model;