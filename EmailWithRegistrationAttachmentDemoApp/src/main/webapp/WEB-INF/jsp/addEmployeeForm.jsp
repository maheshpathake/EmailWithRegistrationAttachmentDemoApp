<h1>Add Employee</h1>
<hr>
<form action="/saveEmployee" method="post" enctype="multipart/form-data">

<table border="1">
<tr>
<td>Name</td> <td><input type="text" placeholder="Name" name="emp_name" /></td>
</tr>

<tr>
<td>Address</td> <td><input type="text" placeholder="Address" name="emp_address" /></td>
</tr>

<tr>
<td>Email</td> <td><input type="text" placeholder="Email" name="emp_email" /></td>
</tr>

<tr>
<td>Attach File</td> <td><input type="file" name="file" /></td>
</tr>

<tr>
 <td><input type="submit" value="Submit" /></td>
</tr>


</table>
</form>