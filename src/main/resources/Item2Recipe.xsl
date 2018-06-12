<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <recipe>
            <xsl:apply-templates/>
            <description>
                <xsl:value-of select="description"/>
            </description> 
            <imagepath>
                <xsl:value-of select="imagepath"/>
            </imagepath>     
        </recipe>        
    </xsl:template>    
    <xsl:template match="id">
        <id>
            <xsl:value-of select="."/>
        </id>    
    </xsl:template>
    <xsl:template match="name">
        <name>
            <xsl:value-of select="."/>
        </name>    
    </xsl:template>
    <xsl:template match="price">
        <price>
            <xsl:value-of select="."/>
        </price>    
    </xsl:template>
    <xsl:template match="description">
        <description>
            <xsl:value-of select="."/>
        </description>    
    </xsl:template>
    <xsl:template match="imagepath">
        <imagepath>
            <xsl:value-of select="."/>
        </imagepath>    
    </xsl:template>
</xsl:stylesheet>