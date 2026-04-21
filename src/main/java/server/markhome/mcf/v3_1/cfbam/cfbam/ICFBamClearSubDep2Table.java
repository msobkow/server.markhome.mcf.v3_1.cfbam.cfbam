
// Description: Java 25 DbIO interface for ClearSubDep2.

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
 *	CFBamClearSubDep2Table database interface for ClearSubDep2
 */
public interface ICFBamClearSubDep2Table
{
	public static final String TABLE_NAME = "ClearSubDep2";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamClearSubDep2 createClearSubDep2( ICFSecAuthorization Authorization,
		ICFBamClearSubDep2 rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamClearSubDep2 updateClearSubDep2( ICFSecAuthorization Authorization,
		ICFBamClearSubDep2 rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteClearSubDep2( ICFSecAuthorization Authorization,
		ICFBamClearSubDep2 rec );
	/**
	 *	Delete the ClearSubDep2 instances identified by the key ClearSubDep1Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 */
	void deleteClearSubDep2ByClearSubDep1Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep1Id );

	/**
	 *	Delete the ClearSubDep2 instances identified by the key ClearSubDep1Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearSubDep2ByClearSubDep1Idx( ICFSecAuthorization Authorization,
		ICFBamClearSubDep2ByClearSubDep1IdxKey argKey );
	/**
	 *	Delete the ClearSubDep2 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep2 key attribute of the instance generating the id.
	 */
	void deleteClearSubDep2ByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep1Id,
		String argName );

	/**
	 *	Delete the ClearSubDep2 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearSubDep2ByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamClearSubDep2ByUNameIdxKey argKey );
	/**
	 *	Delete the ClearSubDep2 instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep2 key attribute of the instance generating the id.
	 */
	void deleteClearSubDep2ByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId );

	/**
	 *	Delete the ClearSubDep2 instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearSubDep2ByClearDepIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByClearDepIdxKey argKey );
	/**
	 *	Delete the ClearSubDep2 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep2 key attribute of the instance generating the id.
	 */
	void deleteClearSubDep2ByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the ClearSubDep2 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearSubDep2ByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByDefSchemaIdxKey argKey );
	/**
	 *	Delete the ClearSubDep2 instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteClearSubDep2ByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the ClearSubDep2 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep2 key attribute of the instance generating the id.
	 */
	void deleteClearSubDep2ByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId );

	/**
	 *	Delete the ClearSubDep2 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearSubDep2ByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey );


	/**
	 *	Read the derived ClearSubDep2 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep2 instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearSubDep2 readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived ClearSubDep2 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep2 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearSubDep2 lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all ClearSubDep2 instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamClearSubDep2[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived ClearSubDep2 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearSubDep2 readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the derived ClearSubDep2 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearSubDep2[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the derived ClearSubDep2 record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearSubDep2[] readDerivedByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the derived ClearSubDep2 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearSubDep2[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the derived ClearSubDep2 record instances identified by the duplicate key ClearSubDep1Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearSubDep2[] readDerivedByClearSubDep1Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ClearSubDep1Id );

	/**
	 *	Read the derived ClearSubDep2 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearSubDep2 readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ClearSubDep1Id,
		String Name );

	/**
	 *	Read the specific ClearSubDep2 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep2 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2 readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific ClearSubDep2 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep2 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2 lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific ClearSubDep2 record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific ClearSubDep2 instances in the database accessible for the Authorization.
	 */
	ICFBamClearSubDep2[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific ClearSubDep2 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2 readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the specific ClearSubDep2 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the specific ClearSubDep2 record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2[] readRecByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the specific ClearSubDep2 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the specific ClearSubDep2 record instances identified by the duplicate key ClearSubDep1Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2[] readRecByClearSubDep1Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ClearSubDep1Id );

	/**
	 *	Read the specific ClearSubDep2 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep1Id	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearSubDep2 readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ClearSubDep1Id,
		String Name );
}
