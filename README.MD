## Equipy

### RestAPI created as a final task in JavaStart Spring course.
#### All frontend created by [JavaStart](https://javastart.pl), backend created by me.

#### Application to simulate borrowing equipment, created with Java and Spring.

#### Endpoints:
* GET /api/users - display all users from database.
* GET /api/users?lastName=value - find user by part of last name.
* POST /api/users - add new user.
* GET /api/users/{id} - get user by id.
* PUT /api/users/{id} - edit user by id.
* GET /api/assets - get all asstes.
* GET /api/assets?text=value - find assets by part of name or serial number.
* GET /api/categories/names - get all categories names.
* POST /api/assets - add new asset.
* GET /api/assets/{id} - find asset by id.
* PUT /api/assets/{id} - edit asset.
* GET /api/users/{userId}/assignments - get all assignments for user with id.
* GET /api/assets/{assetId}/assignments - get all assignments for asset with id.
* POST /api/assignments - assign asset to the user (borrow the asset).
* POST /api/assignments/{assignmentId}/ - give back the asset.

**Licencja**

![license](https://i.creativecommons.org/l/by-nc/3.0/pl/88x31.png)

Ten utwór jest dostępny na licencji [Creative Commons Uznanie autorstwa-Użycie niekomercyjne 3.0 Polska](http://creativecommons.org/licenses/by-nc/3.0/pl/).