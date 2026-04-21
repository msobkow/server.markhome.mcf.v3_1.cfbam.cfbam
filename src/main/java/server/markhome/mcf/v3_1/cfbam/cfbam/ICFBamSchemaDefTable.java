
// Description: Java 25 DbIO interface for SchemaDef.

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
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamobj.*;

/*
 *	CFBamSchemaDefTable database interface for SchemaDef
 */
public interface ICFBamSchemaDefTable
{
	public static final String TABLE_NAME = "SchemaDef";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamSchemaDef createSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamSchemaDef updateSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec );
	/**
	 *	Delete the SchemaDef instances identified by the key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId );

	/**
	 *	Delete the SchemaDef instances identified by the key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByCTenantIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByCTenantIdxKey argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId );

	/**
	 *	Delete the SchemaDef instances identified by the key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByMinorVersionIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByMinorVersionIdxKey argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId,
		String argName );

	/**
	 *	Delete the SchemaDef instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByUNameIdxKey argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argAuthorEMail );

	/**
	 *	Delete the SchemaDef instances identified by the key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByAuthEMailIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByAuthEMailIdxKey argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argProjectURL );

	/**
	 *	Delete the SchemaDef instances identified by the key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByProjectURLIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByProjectURLIdxKey argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argPublishURI );

	/**
	 *	Delete the SchemaDef instances identified by the key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByPubURIIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByPubURIIdxKey argKey );
	/**
	 *	Delete the SchemaDef instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteSchemaDefByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the SchemaDef instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId );

	/**
	 *	Delete the SchemaDef instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaDefByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey );


	/**
	 *	Read the derived SchemaDef record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaDef readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived SchemaDef record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaDef lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all SchemaDef instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaDef readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readDerivedByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId );

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readDerivedByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 MinorVersionId );

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaDef readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 MinorVersionId,
		String Name );

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readDerivedByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaDef[] readDerivedByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaDef readDerivedByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String PublishURI );

	/**
	 *	Read the specific SchemaDef record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific SchemaDef record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific SchemaDef record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SchemaDef instances in the database accessible for the Authorization.
	 */
	ICFBamSchemaDef[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific SchemaDef record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef[] readRecByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId );

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef[] readRecByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 MinorVersionId );

	/**
	 *	Read the specific SchemaDef record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 MinorVersionId,
		String Name );

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef[] readRecByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef[] readRecByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	/**
	 *	Read the specific SchemaDef record instance identified by the unique key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaDef readRecByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 CTenantId,
		String PublishURI );
}
