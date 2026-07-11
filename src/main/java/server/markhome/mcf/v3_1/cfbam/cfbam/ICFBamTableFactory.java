
// Description: Java JPA Factory interface for Table.

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
 *	ICFBamTableFactory interface for Table
 */
public interface ICFBamTableFactory extends ICFBamProtTableFactory
{

	/**
	 *	Allocate a SchemaDefIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableBySchemaDefIdxKey newBySchemaDefIdxKey();

	/**
	 *	Allocate a protected SchemaDefIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableBySchemaDefIdxKey asProtected(ICFBamTableBySchemaDefIdxKey src);

	/**
	 *	Allocate a public SchemaDefIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableBySchemaDefIdxKey asPublic(ICFBamTableBySchemaDefIdxKey src);

	/**
	 *	Allocate a CodeVisIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByCodeVisIdxKey newByCodeVisIdxKey();

	/**
	 *	Allocate a protected CodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByCodeVisIdxKey asProtected(ICFBamTableByCodeVisIdxKey src);

	/**
	 *	Allocate a public CodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByCodeVisIdxKey asPublic(ICFBamTableByCodeVisIdxKey src);

	/**
	 *	Allocate a SchemaCodeVisIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableBySchemaCodeVisIdxKey newBySchemaCodeVisIdxKey();

	/**
	 *	Allocate a protected SchemaCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableBySchemaCodeVisIdxKey asProtected(ICFBamTableBySchemaCodeVisIdxKey src);

	/**
	 *	Allocate a public SchemaCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableBySchemaCodeVisIdxKey asPublic(ICFBamTableBySchemaCodeVisIdxKey src);

	/**
	 *	Allocate a DefSchemaIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByDefSchemaIdxKey asProtected(ICFBamTableByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByDefSchemaIdxKey asPublic(ICFBamTableByDefSchemaIdxKey src);

	/**
	 *	Allocate a UNameIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByUNameIdxKey asProtected(ICFBamTableByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByUNameIdxKey asPublic(ICFBamTableByUNameIdxKey src);

	/**
	 *	Allocate a SchemaCdIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableBySchemaCdIdxKey newBySchemaCdIdxKey();

	/**
	 *	Allocate a protected SchemaCdIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableBySchemaCdIdxKey asProtected(ICFBamTableBySchemaCdIdxKey src);

	/**
	 *	Allocate a public SchemaCdIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableBySchemaCdIdxKey asPublic(ICFBamTableBySchemaCdIdxKey src);

	/**
	 *	Allocate a PrimaryIndexIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByPrimaryIndexIdxKey newByPrimaryIndexIdxKey();

	/**
	 *	Allocate a protected PrimaryIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByPrimaryIndexIdxKey asProtected(ICFBamTableByPrimaryIndexIdxKey src);

	/**
	 *	Allocate a public PrimaryIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByPrimaryIndexIdxKey asPublic(ICFBamTableByPrimaryIndexIdxKey src);

	/**
	 *	Allocate a LookupIndexIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByLookupIndexIdxKey newByLookupIndexIdxKey();

	/**
	 *	Allocate a protected LookupIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByLookupIndexIdxKey asProtected(ICFBamTableByLookupIndexIdxKey src);

	/**
	 *	Allocate a public LookupIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByLookupIndexIdxKey asPublic(ICFBamTableByLookupIndexIdxKey src);

	/**
	 *	Allocate a AltIndexIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByAltIndexIdxKey newByAltIndexIdxKey();

	/**
	 *	Allocate a protected AltIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByAltIndexIdxKey asProtected(ICFBamTableByAltIndexIdxKey src);

	/**
	 *	Allocate a public AltIndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByAltIndexIdxKey asPublic(ICFBamTableByAltIndexIdxKey src);

	/**
	 *	Allocate a QualTableIdx key over Table instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableByQualTableIdxKey newByQualTableIdxKey();

	/**
	 *	Allocate a protected QualTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableByQualTableIdxKey asProtected(ICFBamTableByQualTableIdxKey src);

	/**
	 *	Allocate a public QualTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableByQualTableIdxKey asPublic(ICFBamTableByQualTableIdxKey src);

	/**
	 *	Allocate a Table interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTable newRec();

	/**
	 *	Allocate a protected Table interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTable asProtected(ICFBamTable src);

	/**
	 *	Allocate a public Table interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTable asPublic(ICFBamTable src);

	/**
	 *	Allocate a Table history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamTableH newHRec();

	/**
	 *	Allocate a protected Table history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtTableH asProtected(ICFBamTableH src);

	/**
	 *	Allocate a public Table history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubTableH asPublic(ICFBamTableH src);

}
