<html>
<body>
<g:if test="${error}">
    <p><b>You have errors in form!</b></p>
</g:if>
test
<form method="post" action="/">
    <table>
        <tr>
            <td>First Name:</td>
            <td><input name="firstName" value="${firstName}"/></td>
            <td><div class="fieldErrors"></div></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input name="lastName" value="${lastName}"/></td>
            <td><div class="fieldErrors"></div></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input name="age" value="${age}"/></td>
            <td><div class="fieldErrors"></div></td>
        </tr>
        <tr>
            <td><button type="submit">Submit</button></td>
        </tr>
    </table>
</form>
</body>
</html>