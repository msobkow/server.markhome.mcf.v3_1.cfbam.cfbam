
// Description: Java JPA Factory interface for SchemaDef.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

/*
 *	ICFBamSchemaDefFactory interface for SchemaDef
 */
public interface ICFBamSchemaDefFactory extends ICFBamProtSchemaDefFactory
{

	/**
	 *	Allocate a CTenantIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByCTenantIdxKey newByCTenantIdxKey();

	/**
	 *	Allocate a protected CTenantIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtSchemaDefByCTenantIdxKey asProtected(ICFBamSchemaDefByCTenantIdxKey src);

	/**
	 *	Allocate a public CTenantIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubSchemaDefByCTenantIdxKey asPublic(ICFBamSchemaDefByCTenantIdxKey src);

	/**
	 *	Allocate a MinorVersionIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByMinorVersionIdxKey newByMinorVersionIdxKey();

	/**
	 *	Allocate a protected MinorVersionIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtSchemaDefByMinorVersionIdxKey asProtected(ICFBamSchemaDefByMinorVersionIdxKey src);

	/**
	 *	Allocate a public MinorVersionIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubSchemaDefByMinorVersionIdxKey asPublic(ICFBamSchemaDefByMinorVersionIdxKey src);

	/**
	 *	Allocate a UNameIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtSchemaDefByUNameIdxKey asProtected(ICFBamSchemaDefByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubSchemaDefByUNameIdxKey asPublic(ICFBamSchemaDefByUNameIdxKey src);

	/**
	 *	Allocate a AuthEMailIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByAuthEMailIdxKey newByAuthEMailIdxKey();

	/**
	 *	Allocate a ProjectURLIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByProjectURLIdxKey newByProjectURLIdxKey();

	/**
	 *	Allocate a PubURIIdx key over SchemaDef instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefByPubURIIdxKey newByPubURIIdxKey();

	/**
	 *	Allocate a SchemaDef interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDef newRec();

	/**
	 *	Allocate a protected SchemaDef interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtSchemaDef asProtected(ICFBamSchemaDef src);

	/**
	 *	Allocate a public SchemaDef interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubSchemaDef asPublic(ICFBamSchemaDef src);

	/**
	 *	Allocate a SchemaDef history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamSchemaDefH newHRec();

	/**
	 *	Allocate a protected SchemaDef history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtSchemaDefH asProtected(ICFBamSchemaDefH src);

	/**
	 *	Allocate a public SchemaDef history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubSchemaDefH asPublic(ICFBamSchemaDefH src);

}
