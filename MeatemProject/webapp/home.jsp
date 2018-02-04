<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="announcements.AnnouncementDAO"%>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd%22%3E
<html xmlns="http://www.w3.org/1999/xhtml%22%3E

<head>
<title>Meatem</title>
<link rel="stylesheet" href="Styles/Style.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"/>
		<link rel='stylesheet prefetch'
			href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'/>
			<link rel="stylesheet" href="css/style.css"/>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"/>
		<link rel='stylesheet prefetch'
			href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<div class="header">
	<h2>Meatem Project</h2>
</div>
<div id="navbar">
	<a class="active" href="javascript:void(0)">Home</a> <a href="#"
		id="icon-bar"><i class="fa fa-search"></i> Szukaj</a>
	<div class="navbar-right">
		<a href="#" id="icon-bar"
			onclick="document.getElementById('id01').style.display='block'"><i
			class="fa fa-chevron-right"></i> Logowanie</a>
	</div>

</div>

<div id="id01" class="modal">
	<div class="logmod" id="hidden">
		<div class="logmod__wrapper">
			<span class="logmod__close">Close</span>
			<div class="logmod__container">
				<ul class="logmod__tabs">
					<li data-tabtar="lgm-2"><a href="#">Login</a></li>
					<li data-tabtar="lgm-1"><a href="#">Sign Up</a></li>
				</ul>
				<div class="logmod__tab-wrapper">
					<div class="logmod__tab lgm-1">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Enter your personal
								details <strong>to create an acount</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" action="#" class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-name">Email*</label>
										<input class="string optional" maxlength="255" id="user-email"
											placeholder="Email" type="email" size="50" />
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="user-pw">Password
											*</label> <input class="string optional" maxlength="255" id="user-pw"
											placeholder="Password" type="text" size="50" />
									</div>
									<div class="input string optional">
										<label class="string optional" for="user-pw-repeat">Repeat
											password *</label> <input class="string optional" maxlength="255"
											id="user-pw-repeat" placeholder="Repeat password" type="text"
											size="50" />
									</div>
								</div>
								<div class="simform__actions">
									<input type="submit" class="sumbit" name="commit" type="sumbit"
										value="Create Account" /> <span
										class="simform__actions-sidetext">By creating an
										account you agree to our <a class="special" href="#"
										target="_blank" role="link">Terms & Privacy</a>
									</span>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" class="connect facebook">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-facebook"></i>
									</div>
									</div>
									<div class="connect__context">
										<span>Create an account with <strong>Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-google-plus"></i>
									</div>
									</div>
									<div class="connect__context">
										<span>Create an account with <strong>Google+</strong></span>
									</div>
								</a>
								<a href="#" class="connect twitter">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-twitter"></i>
										</div>
									</div>
									<div class="connect__context">
										<span>Create an account with <strong>Twitter</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
					<div class="logmod__tab lgm-2">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Enter your email
								and password <strong>to sign in</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" action="#" class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-name">Email*</label>
										<input class="string optional" maxlength="255" id="user-email"
											placeholder="Email" type="email" size="50" />
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-pw">Password
											*</label> <input class="string optional" maxlength="255" id="user-pw"
											placeholder="Password" type="password" size="50" /> <span
											class="hide-password">Show</span>
									</div>
								</div>
								<div class="simform__actions">
									<input type ="submit" class="sumbit" name="commit" type="sumbit"
										value="Log In" /> <span class="simform__actions-sidetext"><a
										class="special" role="link" href="#">Forgot your password?<br>Click
												here</a></span>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" class="connect facebook">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-facebook"></i>
										</div>
									</div>
									<div class="connect__context">
										<span>Sign in with <strong>Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-google-plus"></i>
										</div>
									</div>
									<div class="connect__context">
										<span>Sign in with <strong>Google+</strong></span>
									</div>
								</a>
								<a href="#" class="connect twitter">
									<div class="connect__icon">
									<div class="ico">
										<i class="fa fa-twitter"></i>
										</div>
									</div>
									<div class="connect__context">
										<span>Sign in with <strong>Twitter</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<script>
	// Get the modal
	var hidden = document.getElementById('hidden');
	var modal = document.getElementById('id01');
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == hidden) {
			modal.style.display = "none";
		}
	}
</script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>
var LoginModalController = {
	    tabsElementName: ".logmod__tabs li",
	    tabElementName: ".logmod__tab",
	    inputElementsName: ".logmod__form .input",
	    hidePasswordName: ".hide-password",
	    
	    inputElements: null,
	    tabsElement: null,
	    tabElement: null,
	    hidePassword: null,
	    
	    activeTab: null,
	    tabSelection: 0, // 0 - first, 1 - second
	    
	    findElements: function () {
	        var base = this;
	        
	        base.tabsElement = $(base.tabsElementName);
	        base.tabElement = $(base.tabElementName);
	        base.inputElements = $(base.inputElementsName);
	        base.hidePassword = $(base.hidePasswordName);
	        
	        return base;
	    },
	    
	    setState: function (state) {
	    	var base = this,
	            elem = null;
	        
	        if (!state) {
	            state = 0;
	        }
	        
	        if (base.tabsElement) {
	        	elem = $(base.tabsElement[state]);
	            elem.addClass("current");
	            $("." + elem.attr("data-tabtar")).addClass("show");
	        }
	  
	        return base;
	    },
	    
	    getActiveTab: function () {
	        var base = this;
	        
	        base.tabsElement.each(function (i, el) {
	           if ($(el).hasClass("current")) {
	               base.activeTab = $(el);
	           }
	        });
	        
	        return base;
	    },
	   
	    addClickEvents: function () {
	    	var base = this;
	        
	        base.hidePassword.on("click", function (e) {
	            var $this = $(this),
	                $pwInput = $this.prev("input");
	            
	            if ($pwInput.attr("type") == "password") {
	                $pwInput.attr("type", "text");
	                $this.text("Hide");
	            } else {
	                $pwInput.attr("type", "password");
	                $this.text("Show");
	            }
	        });
	 
	        base.tabsElement.on("click", function (e) {
	            var targetTab = $(this).attr("data-tabtar");
	            
	            e.preventDefault();
	            base.activeTab.removeClass("current");
	            base.activeTab = $(this);
	            base.activeTab.addClass("current");
	            
	            base.tabElement.each(function (i, el) {
	                el = $(el);
	                el.removeClass("show");
	                if (el.hasClass(targetTab)) {
	                    el.addClass("show");
	                }
	            });
	        });
	        
	        base.inputElements.find("label").on("click", function (e) {
	           var $this = $(this),
	               $input = $this.next("input");
	            
	            $input.focus();
	        });
	        
	        return base;
	    },
	    
	    initialize: function () {
	        var base = this;
	        
	        base.findElements().setState().getActiveTab().addClickEvents();
	    }
	};

	$(document).ready(function() {
	    LoginModalController.initialize();
	});
</script>
<a href="index.jsp">wystawianie ogloszen</a>
<a href="SignUp.jsp">Rejestracja</a>
<a href="SignIn.jsp">Logowanie</a>
<a href="Announcements">Zobacz Ogloszenia</a>
<a href="Groups">Przegladaj Grupy</a>
<a href="CreateGroup.jsp">Zaloz Grupe!</a>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>
A<br></br>

<script>
	window.onscroll = function() {
		myFunction()
	};

	var navbar = document.getElementById("navbar");
	var sticky = navbar.offsetTop;

	function myFunction() {
		if (window.pageYOffset >= sticky) {
			navbar.classList.add("sticky")
		} else {
			navbar.classList.remove("sticky");
		}
	}
</script>

</head>
<body>

	<%
		String user = null;
		if (session.getAttribute("user") == null) {
	%><h3>Nie zalogowano</h3>
	<%
		} else {
			user = (String) session.getAttribute("user");
			String userName = null;
			String sessionID = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user"))
						userName = cookie.getValue();
					if (cookie.getName().equals("JSESSIONID"))
						sessionID = cookie.getValue();
				}
			}

			AnnouncementDAO dao = new AnnouncementDAO();

			if (dao.isAdmin((String) session.getAttribute("user"))) {
				if (request.getParameter("backup") != null) {
					String dbName = "announcements";
					String dbUser = "root";
					String dbPass = "supersilnehaslo123";
					String executeCmd = "";
					executeCmd = "mysqldump " + dbName + " -u " + dbUser + " -p" + dbPass + " > E:/backup.sql";
					System.out.println(executeCmd);
					//Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
					Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "bash", "-c", executeCmd });
					int processComplete = runtimeProcess.waitFor();
					if (processComplete == 0) {
						System.out.println("Backup taken successfully");
					} else {
						System.out.println("Could not take mysql backup");
					}
				}
				if (request.getParameter("restorebackup") != null) {
					String dbName = "TESTDB";
					String dbUser = "root";
					String dbPass = "supersilnehaslo123";
					String com = "mysql -u " + dbUser + " -p" + dbPass + " " + dbName + " < E:/backup.sql";
					System.out.println(com);
					String[] executeCmd = { "bash", "-c", com };
					Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
					int processComplete = runtimeProcess.waitFor();
					if (processComplete == 0) {
						System.out.println("success");
					} else {
						System.out.println("restore failure");
					}
				}
	%>
	<form action="home.jsp" method="post">
		<input type="submit" value="Zrob backup!"> <input
			type="hidden" name="backup" value="true">
	</form>
	<form action="home.jsp" method="post">
		<input type="submit" value="Odzyskaj backup!"> <input
			type="hidden" name="restorebackup" value="true">
	</form>
	<%
		}
	%>


	<h3>
		Czesc
		<%=session.getAttribute("user")%></h3>
	<a href="UserPanel.jsp">Moje konto</a>
	<br>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="Wyloguj">
		</form> <%
 	}
 %>
	
</body>
</html>