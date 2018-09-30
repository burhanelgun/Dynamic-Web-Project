<html>

<head>
<style type="text/css">
body
{
    background: url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed;
    background-size: cover;
    padding: 0;
    margin: 0;
}
form.login input[type="submit"]
{
    width: 100%;
    margin-left: -40px;
    font-size: 14px;
    text-transform: uppercase;
    font-weight: 500;
    margin-top: 16px;
    outline: 0;
    cursor: pointer;
    letter-spacing: 1px;
}

form.login input[type="submit"]:hover
{
    transition: background-color 0.5s ease;
}


</head>
</style>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
User: <b><%= request.getRemoteUser() %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="wrap">
                <p class="form-title">
                    Register</p>
                <form class="login" action="RegisterPage.jsp">
                <input type="submit" value="REGISTER" class="btn btn-success btn-sm" />
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                        </div>

                    </div>
                </div>
                </form>
                
            </div>
        </div>
        
                <div class="col-md-12">
            <div class="wrap">
                <p class="form-title">
                    Register</p>
                <form class="login" action="AddNewPhonePage.jsp">
                <input type="submit" value="UPDATE PHONE" class="btn btn-success btn-sm" />
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                        </div>

                    </div>
                </div>
                </form>
                
            </div>
        </div>
    </div>
</div>

</html>
