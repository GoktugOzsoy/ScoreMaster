🌍 Dil Değiştirme:<br>
🇹🇷 Türkçe için, [buraya](README_TR.md) tıklayın.<br><br>

🏆 ScoreMaster <br>
&nbsp;&nbsp;&nbsp;This JavaFX application is a basic user interface (UI) designed to manage different user portals and functionalities. The application includes various buttons, input fields, checkboxes, and table views, each with custom CSS styling for an improved user experience. The code handles user authentication and management features, with components for Admin and User portals. The application interacts with a database to handle user data and display it dynamically.<br><br>

🚀 Features<br>
&nbsp;&nbsp;&nbsp;🖥️ User Portals<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Admin Portal: Manage system settings and user data.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- User Portal: Access user-specific functionality.<br><br>
&nbsp;&nbsp;&nbsp;🔒 Security<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Secure login and password management.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Role-based access for admins and users.<br><br>
&nbsp;&nbsp;&nbsp;📦 Database Integration<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Data is fetched and managed via a MySQL database.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Combined views for unified user and role data.<br><br>
&nbsp;&nbsp;&nbsp;📊 Database Views<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Combined SQL view for seamless data retrieval:<br>
`CREATE VIEW user_role_view AS
 SELECT users.user_id, users.username, users.mail_address, roles.role_name
 FROM users
 JOIN roles ON  users.role_id = roles.role_id;`<br><br>
&nbsp;&nbsp;&nbsp;💡 Enhanced UI<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Modern design using JavaFX and CSS.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Custom buttons and forms with hover/pressed effects.<br><br><br>


📚 Usage<br>
&nbsp;&nbsp;&nbsp;1️⃣ Run the Application.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To start the application, simply run the main JavaFX class.<br>
`mvn clean javafx:run`<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This will launch the application and present you with the login screen.<br>
&nbsp;&nbsp;&nbsp;2️⃣ Database Connection<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The application establishes a connection to the MySQL database:
