
// Description: Java 25 DbIO interface for DelTopDep.

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
 *	CFBamDelTopDepTable database interface for DelTopDep
 */
public interface ICFBamDelTopDepTable
{
	public static final String TABLE_NAME = "DelTopDep";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamDelTopDep createDelTopDep( ICFSecAuthorization Authorization,
		ICFBamDelTopDep rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamDelTopDep updateDelTopDep( ICFSecAuthorization Authorization,
		ICFBamDelTopDep rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteDelTopDep( ICFSecAuthorization Authorization,
		ICFBamDelTopDep rec );
	/**
	 *	Delete the DelTopDep instances identified by the key DelTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDelTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId );

	/**
	 *	Delete the DelTopDep instances identified by the key DelTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByDelTopDepTblIdx( ICFSecAuthorization Authorization,
		ICFBamDelTopDepByDelTopDepTblIdxKey argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId,
		String argName );

	/**
	 *	Delete the DelTopDep instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamDelTopDepByUNameIdxKey argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the DelTopDep instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamDelTopDepByPrevIdxKey argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the DelTopDep instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByNextIdx( ICFSecAuthorization Authorization,
		ICFBamDelTopDepByNextIdxKey argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the DelTopDep instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamDelDepByDefSchemaIdxKey argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId );

	/**
	 *	Delete the DelTopDep instances identified by the key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByDelDepIdx( ICFSecAuthorization Authorization,
		ICFBamDelDepByDelDepIdxKey argKey );
	/**
	 *	Delete the DelTopDep instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteDelTopDepByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the DelTopDep instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId );

	/**
	 *	Delete the DelTopDep instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteDelTopDepByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey );


	/**
	 *	Read the derived DelTopDep record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelTopDep instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamDelTopDep readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived DelTopDep record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamDelTopDep lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all DelTopDep instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived DelTopDep record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamDelTopDep readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key DelTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByDelTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId );

	/**
	 *	Read the derived DelTopDep record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamDelTopDep readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived DelTopDep record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamDelTopDep[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read the specific DelTopDep record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific DelTopDep record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelTopDep instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific DelTopDep record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific DelTopDep instances in the database accessible for the Authorization.
	 */
	ICFBamDelTopDep[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific DelTopDep record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TenantId );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key DelTopDepTblIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByDelTopDepTblIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId );

	/**
	 *	Read the specific DelTopDep record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific DelTopDep record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamDelTopDep[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamDelTopDep moveRecUp( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );

	/**
	 *	Move the specified record down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamDelTopDep moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );
}
