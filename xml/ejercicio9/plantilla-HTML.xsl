<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes"/>

	<xsl:template match="/">
		<html>
			<head>
				<META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
				<title>Poema</title>
			</head>
			<body>
				<h1>
					<xsl:value-of select="poema/titulo" />
				</h1>
				<h2>
					<xsl:value-of select="poema/@fecha" />
				</h2>
				<h2>
					<em>
						<xsl:value-of select="poema/@lugar" />
					</em>
				</h2>
				<xsl:for-each select="poema/verso">
					<p>
						<xsl:value-of select="."/>
					</p>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>

