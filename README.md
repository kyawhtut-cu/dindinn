## DinDinn

This repo is use for job interview code test. I'm using in MVVM pattern with Repository. And then, I have implemented Github Action for CI/CD. Currently, `expired_time`, `created_at`, `alerted_at` fields value are hardcode because the order json api is mock. That are replaced with current time. So, `created_at` value is current time, `expired_time` value is current time + 6 min and `alerted_at` value is current time + 5 min.

Github Action
--
Github Action will run after create new tag for new version. But, `unit-test.yml` will run after code `push` to master branch. 

Use of support lib
-
 > [gradle-android-git-version](https://github.com/gladed/gradle-android-git-version) - that is use to change android version name and version code when new version release and auto generate version code and version name.
 > 
 > [dagger-hilt](https://dagger.dev/hilt/) - that is use for dependency injection
 > 
 > [data-binding](https://developer.android.com/topic/libraries/data-binding)
 > 
 > [retrofit](https://github.com/square/retrofit) - networking
 > 
 > [glide](https://github.com/bumptech/glide) - image
 > 
 > [timber](https://github.com/JakeWharton/timber) - logging
 > 
 > [material android](https://github.com/material-components/material-components-android)
 >
 > [github-action](https://github.com/features/actions) - that is use for CI/CD
 >
 > [mockito](https://site.mockito.org/) - that is use for unit testing

Implementation
-
  OrderActivity
  [OrderRequest](images/order-activity.png)
  
  Ingredient Activity
  [CategoryRequest](images/ingredient-category.png)
  
  [IngredientRequestByCategoryID](images/ingredient-activity.png)
  