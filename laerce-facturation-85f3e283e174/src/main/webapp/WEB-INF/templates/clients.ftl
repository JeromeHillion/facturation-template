<html>
<head>
    <title>Liste des clients</title>
</head>
<h1>Clients</h1>
<table>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Ville</th>
        <th>Pays</th>
    </tr>
    <#list clients as clt>
        <tr>
            <td>${clt.nom}</td>
            <td>${clt.pnom}</td>
            <td>${clt.loc}</td>
            <td>${clt.pays}</td>
        </tr>
    </#list>
</table>
</html>