<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Web Search Engine</title>
    <link href="/static/myStyle.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
      body {
        background-image: url("/static/2.jpg");
      }
    </style>
  </head>
  <body>
        <br><br><br>
      <h1>
        <p style="text-align: center;
                  padding-top:100px;
                  color:#b1bfca;
                  font-size: 120px;
                  font-family: 'Adobe Garamond Pro'">
          Web Search Engine
        </p>
      </h1>
      <br><br>

      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="input-group" id="adv-search">
              <div class="input-group-btn">
                <div class="btn-group" role="group">
                  <form action="WordContServlet" method="post">
                    <input type="text" class="form-control" name="inputbox" placeholder="Search here" />
                    <button type="submit" class="btn btn-primary">
                      <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>

  </body>
</html>