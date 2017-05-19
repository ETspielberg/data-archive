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
				<link rel="icon" href="img/favicon.ico" />

				<title>DUDE :: Upload</title>
				<script type="text/javascript"
					src="{$WebApplicationBaseURL}webjars/jquery/${version.jquery}/jquery.min.js"></script>
				<script type="text/javascript"> jQuery.noConflict(); </script>
				<script type="text/javascript"
					src="{$WebApplicationBaseURL}webjars/jquery-ui/${version.jquery}/jquery-ui.min.js"></script>
				<script src="{$WebApplicationBaseURL}js/dropzone.js"></script>


				<link href="{$WebApplicationBaseURL}css/bootstrap.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/ie10-viewport-bug-workaround.css"
					rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/dashboard.css" rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/description_box.css"
					rel="stylesheet" />
				<link href="{$WebApplicationBaseURL}css/dropzone.css" rel="stylesheet" />
			</head>

			<body>



				<!-- Main jumbotron for a primary marketing message or call to action -->
				<div class="container">
					<div class="jumbotron">

						<h1>DUDE</h1>
						<p>Daten@UDE - The data archive of the University Duisburg-Essen</p>
					</div>


					<form class="form-horizontal">


						<!-- Text input -->
						<div class="col-md-10 col-md-offset-1 main">
							<div class="form-group">
								<label class="col-md-4 control-label" for="textinput">Title</label>
								<div class="col-md-5">
									<input id="textinput" name="textinput" placeholder="Title"
										class="form-control input-md" required="" type="text" />
									<span class="help-block">Please insert the title of your Dataset</span>
								</div>

								<label class="col-md-4 control-label" for="author">Author</label>
								<div class="col-md-5">
									<div class="input-group">
										<input id="author" name="author" class="form-control"
											placeholder="Author name" required="" type="text" />
										<span class="input-group-addon">add</span>
									</div>
									<p class="help-block">Please insert author name and press "add"</p>
								</div>

								<label class="col-md-4 control-label" for="Related Publikation">Related
									Publication</label>
								<div class="col-md-5">
									<div class="input-group">
										<input id="Related Publikation" name="Related Publikation"
											class="form-control" placeholder="DOI, URN, ..." type="text" />
										<span class="input-group-addon">Search</span>
									</div>
									<p class="help-block">If existent, please insert the persistent identifier
										of the corresponding publication</p>
								</div>

								<label class="col-md-4 control-label" for="Publicity">Publicity</label>
								<div class="col-md-4">
									<div class="radio">
										<label for="Publicity-0">
											<input name="Publicity" id="Publicity-0" value="1"
												checked="checked" type="radio" />
											publish immediatly
										</label>
									</div>
									<div class="radio">
										<label for="Publicity-1">
											<input name="Publicity" id="Publicity-1" value="2"
												type="radio" />
											publish in one year
										</label>
									</div>
									<div class="radio">
										<label for="Publicity-2">
											<input name="Publicity" id="Publicity-2" value="3"
												type="radio" />
											publish in five years
										</label>
									</div>
									<div class="radio">
										<label for="Publicity-3">
											<input name="Publicity" id="Publicity-3" value="4"
												type="radio" />
											do not publish at all
										</label>
									</div>
								</div>
							</div>

							<!-- Button (Double) -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="button1id">Finish</label>
								<div class="col-md-8">
									<button id="button1id" name="button1id" class="btn btn-success">Submit</button>
									<button id="button2id" name="button2id" class="btn btn-danger">Cancel</button>
								</div>
							</div>
						</div>
					</form>

					<form action="uploadSave" method="post" class="dropzone"
						enctype="multipart/form-data" />

				</div>
				<script src="{$WebApplicationBaseURL}js/bootstrap.min.js" />
				<script src="{$WebApplicationBaseURL}js/ie10-viewport-bug-workaround.js"></script>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
