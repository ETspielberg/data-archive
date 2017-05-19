<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xalan="http://xml.apache.org/xalan"
	xmlns:i18n="xalan://org.mycore.services.i18n.MCRTranslation"
	xmlns:mabxml="http://www.ddb.de/professionell/mabxml/mabxml-1.xsd"
	exclude-result-prefixes="xsl xalan i18n mabxml">

	<xsl:output encoding="UTF-8" method="html" media-type="text/html"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
		doctype-system="http://www.w3.org/TR/html401/loose.dtd" indent="yes"
		xalan:indent-amount="2" />

	<!-- ============ Parameter von MyCoRe LayoutService ============ -->

	<xsl:param name="WebApplicationBaseURL" />
	<xsl:param name="ServletsBaseURL" />
	<xsl:param name="RequestURL" />
	<xsl:param name="CurrentLang" />
	<xsl:param name="DefaultLang" />
	<xsl:param name="username" />

	<!-- ======== HTML Seitenlayout ======== -->


	<xsl:variable name="page.title">
		<xsl:text>Bestandspflege - Management von</xsl:text>
		<xsl:value-of select="/stockControlManagement/@username" />
	</xsl:variable>
	
	<xsl:variable name="username">
		<xsl:value-of select="/start/@username" />
	</xsl:variable>
	
	<xsl:variable name="email">
		<xsl:value-of select="/start/@email" />
	</xsl:variable>


	<xsl:template match="/">
	<html>
			<head>
				<meta charset="utf-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge" />
				<meta name="viewport" content="width=device-width, initial-scale=1" />
				<!-- The above 3 meta tags *must* come first in the head; any other head 
					content must come *after* these tags -->
				<meta name="description" content="" />
				<meta name="author" content="" />
				<link rel="icon" href="img/favicon.ico">
				</link>

				<title>DUDE :: Start</title>

				<script	type="text/javascript" src="{$WebApplicationBaseURL}webjars/jquery/${version.jquery}/jquery.min.js"></script>
				<script type="text/javascript"> jQuery.noConflict(); </script>
				<script type="text/javascript" src="{$WebApplicationBaseURL}webjars/jquery-ui/${version.jquery}/jquery-ui.min.js"></script>
				<script src="{$WebApplicationBaseURL}js/dropzone.js"></script>


				<link href="{$WebApplicationBaseURL}css/bootstrap.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/dashboard.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/description_box.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/dropzone.css" rel="stylesheet" />
				
				<link rel="icon" href="{$WebApplicationBaseURL}img/favicon.ico"/>
			</head>
			<body>
				<nav class="navbar navbar-inverse navbar-fixed-top">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse" data-target="#navbar" aria-expanded="false"
								aria-controls="navbar">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="{$WebApplicationBaseURL}dataArchive/start">DUDE</a>
						</div>
						<div id="navbar" class="navbar-collapse collapse">
							<ul class="nav navbar-nav navbar-right">
								<li>
									<a href="{$WebApplicationBaseURL}fachref/about.html">Info</a>
								</li>
								<li>
									<a href="{$WebApplicationBaseURL}dataArchive/upload">Daten archivieren</a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b><xsl:value-of select="start/@loggedInAs" /> </b><span class="caret"></span></a>
									<ul class="dropdown-menu" id="login-dp">
										<li><a href="{$WebApplicationBaseURL}logout">Logout</a></li>
										<li><a href="{$WebApplicationBaseURL}forms/User_Form.xed">Einstellungen</a></li>
										<li><a href="{$WebApplicationBaseURL}dataArchive/passwordChange">Passwort ändern</a></li>
										<xsl:if test="start/@userAdmin">
											<li><a href="{$WebApplicationBaseURL}dataArchive/adminOverview">Admin</a></li>
										</xsl:if>
									</ul>
								</li>
							</ul>
						</div><!--/.navbar-collapse -->
					</div>
				</nav>
 
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container mainbox">
        <h1>Willkommen bei Daten@UDE,<br />
        <xsl:value-of select="start/@loggedInAs" /></h1>
        <p>Dem Datenarchiv der Universität Duisburg-Essen </p>
        <p><a class="btn btn-primary btn-lg" href="{$WebApplicationBaseURL}forms/User_Form.xed" role="button">Meine Einstellungen &#187;</a></p>
      </div>
    </div>
    <div class="container mainbox">
      <div class="row">
        <div class="col-md-3">
          <h2>Daten archivieren</h2>
          <p> In drei Schritten Daten hochladen, beschreiben und zuverlässig speichern.</p>
          <p><a class="btn btn-success" href="upload" role="button">Daten archivieren &#187;</a></p>
        </div>
        <div class="col-md-3">
          <h2>Daten abrufen</h2>
          <p> Mittels eines PID einen Datensatz aus dem Daten-Archiv wiederholen.</p>
          <p><a class="btn btn-success" href="download" role="button">Daten abrufen &#187;</a></p>
       </div>
       </div>
      <hr />
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="{$WebApplicationBaseURL}js/bootstrap.min.js"/>
    <script src="{$WebApplicationBaseURL}js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
</xsl:template>
</xsl:stylesheet>
