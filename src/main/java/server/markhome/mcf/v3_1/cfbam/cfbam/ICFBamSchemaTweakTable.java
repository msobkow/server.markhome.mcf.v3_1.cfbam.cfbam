
// Description: Java 25 DbIO interface for SchemaTweak.

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
 *	CFBamSchemaTweakTable database interface for SchemaTweak
 */
public interface ICFBamSchemaTweakTable
{

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamSchemaTweak createSchemaTweak( ICFSecAuthorization Authorization,
		ICFBamSchemaTweak rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamSchemaTweak updateSchemaTweak( ICFSecAuthorization Authorization,
		ICFBamSchemaTweak rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteSchemaTweak( ICFSecAuthorization Authorization,
		ICFBamSchemaTweak rec );
	/**
	 *	Delete the SchemaTweak instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId );

	/**
	 *	Delete the SchemaTweak instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakBySchemaIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaTweakBySchemaIdxKey argKey );
	/**
	 *	Delete the SchemaTweak instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteSchemaTweakByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the SchemaTweak instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName );

	/**
	 *	Delete the SchemaTweak instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamTweakByUNameIdxKey argKey );
	/**
	 *	Delete the SchemaTweak instances identified by the key ValTentIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakByValTentIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId );

	/**
	 *	Delete the SchemaTweak instances identified by the key ValTentIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakByValTentIdx( ICFSecAuthorization Authorization,
		ICFBamTweakByValTentIdxKey argKey );
	/**
	 *	Delete the SchemaTweak instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId );

	/**
	 *	Delete the SchemaTweak instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakByScopeIdx( ICFSecAuthorization Authorization,
		ICFBamTweakByScopeIdxKey argKey );
	/**
	 *	Delete the SchemaTweak instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the SchemaTweak instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamTweakByDefSchemaIdxKey argKey );
	/**
	 *	Delete the SchemaTweak instances identified by the key UDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaTenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 */
	void deleteSchemaTweakByUDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argDefSchemaTenantId,
		CFLibDbKeyHash256 argDefSchemaId,
		String argName );

	/**
	 *	Delete the SchemaTweak instances identified by the key UDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSchemaTweakByUDefIdx( ICFSecAuthorization Authorization,
		ICFBamTweakByUDefIdxKey argKey );


	/**
	 *	Read the derived SchemaTweak record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaTweak instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaTweak readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived SchemaTweak record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaTweak instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaTweak lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all SchemaTweak instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamSchemaTweak[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived SchemaTweak record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaTweak readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the derived SchemaTweak record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaTweak readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Read an array of the derived SchemaTweak record instances identified by the duplicate key ValTentIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaTweak[] readDerivedByValTentIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the derived SchemaTweak record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaTweak[] readDerivedByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId );

	/**
	 *	Read an array of the derived SchemaTweak record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaTweak[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read the derived SchemaTweak record instance identified by the unique key UDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaTenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamSchemaTweak readDerivedByUDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	/**
	 *	Read an array of the derived SchemaTweak record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamSchemaTweak[] readDerivedBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Read the specific SchemaTweak record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaTweak instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific SchemaTweak record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaTweak instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific SchemaTweak record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SchemaTweak instances in the database accessible for the Authorization.
	 */
	ICFBamSchemaTweak[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific SchemaTweak record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the specific SchemaTweak record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Read an array of the specific SchemaTweak record instances identified by the duplicate key ValTentIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak[] readRecByValTentIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the specific SchemaTweak record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak[] readRecByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId );

	/**
	 *	Read an array of the specific SchemaTweak record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read the specific SchemaTweak record instance identified by the unique key UDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	ScopeId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaTenantId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak readRecByUDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	/**
	 *	Read an array of the specific SchemaTweak record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The SchemaTweak key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamSchemaTweak[] readRecBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 SchemaDefId );
}
