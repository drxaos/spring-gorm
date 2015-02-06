<html>
<body>
<g:each in="${list}" var="person">
    <p>${person.firstName} ${person.lastName} (${ages[person.id]})</p>
</g:each>
</body>
</html>