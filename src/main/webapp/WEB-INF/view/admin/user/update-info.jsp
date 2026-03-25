<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update User Information</title>
    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="/css/demo.css" rel="stylesheet" />
  </head>
  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6 col-12 mx-auto">
          <h3>Update User Information</h3>
          <hr />
          <form:form
            method="post"
            action="/admin/user/update"
            modelAttribute="userDetails"
          >
            <div class="mb-3">
              <label class="form-label">Id: </label>
              <form:input
                type="text"
                class="form-control"
                path="id"
                readonly="true"
              />
            </div>

            <div class="mb-3">
              <label class="form-label">Email: </label>
              <form:input type="email" class="form-control" path="email" readonly="true" />
            </div>

            <div class="mb-3">
              <label class="form-label">Phone number: </label>
              <form:input type="text" class="form-control" path="phone" />
            </div>

            <div class="mb-3">
              <label class="form-label">Full name: </label>
              <form:input type="text" class="form-control" path="fullName" />
            </div>

            <div class="mb-3">
              <label class="form-label">Address: </label>
              <form:input type="text" class="form-control" path="address" />
            </div>
            <div class="d-flex justify-content-between">
              <a href="/admin/user" class="btn btn-primary mt-2">Back</a>
              <button class="btn btn-warning" type="submit">Update</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </body>
</html>
