# MEET YOUR PLANET

This project is built in:

- Frontend:
  - (Kotlin) Android
- Backend:
  - NodeJS
  - Express
  - MongoDB

And licensed by GPL v3.

## Database E-R

![alt text](./docs/E-R_diagram.jpeg "E-R Diagram")


## Backend: Routes

Description | Method | Route
--- | --- | ---
Login | POST | /login
New user | POST | /login/new
--- | --- | ---
Get Systems | GET | /api/systems
Get System By Id | GET | /api/systems/{id}
Add System | POST | /api/systems
Update System | PUT | /api/systems/{id}
Delete System | DELETE | /api/systems/{id}
--- | --- | ---
Get All Planets| GET | /api/planets
Get Planets From System| GET | /api/systems/{id}/planets
Add Planet | POST | /api/planets
Update Planet | PUT | /api/planets/{id}
Delete Planet | DELETE | /api/planets/{id}
