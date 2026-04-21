
// Description: Java 25 DbIO interface for ClearTopDep.

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
 *	CFBamClearTopDepTable database interface for ClearTopDep
 */
public interface ICFBamClearTopDepTable
{
	public static final String TABLE_NAME = "ClearTopDep";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamClearTopDep createClearTopDep( ICFSecAuthorization Authorization,
		ICFBamClearTopDep rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamClearTopDep updateClearTopDep( ICFSecAuthorization Authorization,
		ICFBamClearTopDep rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteClearTopDep( ICFSecAuthorization Authorization,
		ICFBamClearTopDep rec );
	/**
	 *	Delete the ClearTopDep instances identified by the key ClrTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByClrTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId );

	/**
	 *	Delete the ClearTopDep instances identified by the key ClrTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByClrTopDepTblIdx( ICFSecAuthorization Authorization,
		ICFBamClearTopDepByClrTopDepTblIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId,
		String argName );

	/**
	 *	Delete the ClearTopDep instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamClearTopDepByUNameIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the ClearTopDep instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamClearTopDepByPrevIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the ClearTopDep instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByNextIdx( ICFSecAuthorization Authorization,
		ICFBamClearTopDepByNextIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId );

	/**
	 *	Delete the ClearTopDep instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByClearDepIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByClearDepIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the ClearTopDep instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByDefSchemaIdxKey argKey );
	/**
	 *	Delete the ClearTopDep instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteClearTopDepByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the ClearTopDep instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId );

	/**
	 *	Delete the ClearTopDep instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteClearTopDepByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey );


	/**
	 *	Read the derived ClearTopDep record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearTopDep instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearTopDep readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived ClearTopDep record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearTopDep lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all ClearTopDep instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived ClearTopDep record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearTopDep readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key ClrTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByClrTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId );

	/**
	 *	Read the derived ClearTopDep record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamClearTopDep readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived ClearTopDep record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamClearTopDep[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read the specific ClearTopDep record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific ClearTopDep record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific ClearTopDep record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific ClearTopDep instances in the database accessible for the Authorization.
	 */
	ICFBamClearTopDep[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific ClearTopDep record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key ClrTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByClrTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId );

	/**
	 *	Read the specific ClearTopDep record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific ClearTopDep record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamClearTopDep[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamClearTopDep moveRecUp( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );

	/**
	 *	Move the specified record down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamClearTopDep moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );
}
