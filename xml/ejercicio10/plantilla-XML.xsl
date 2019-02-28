<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes" />


	<xsl:template match="/">
		<poema fecha="{html/body/h2}" lugar="{html/body/h2/em}">
			<titulo>
				<xsl:value-of select="html/body/h1" />
			</titulo>
			<xsl:for-each select="html/body/p">
				<verso>
					<xsl:value-of select="." />
				</verso>
			</xsl:for-each>
		</poema>
	</xsl:template>

</xsl:stylesheet>

