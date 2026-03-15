
// Description: Java 25 DbIO interface for RelationCol.

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
 *	CFBamRelationColTable database interface for RelationCol
 */
public interface ICFBamRelationColTable
{

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamRelationCol createRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamRelationCol updateRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec );
	/**
	 *	Delete the RelationCol instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteRelationColByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the RelationCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		String argName );

	/**
	 *	Delete the RelationCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByUNameIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId );

	/**
	 *	Delete the RelationCol instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByRelationIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelationIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the RelationCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByDefSchemaIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argFromColId );

	/**
	 *	Delete the RelationCol instances identified by the key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByFromColIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByFromColIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argToColId );

	/**
	 *	Delete the RelationCol instances identified by the key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByToColIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByToColIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the RelationCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByPrevIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the RelationCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByNextIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByNextIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the RelationCol instances identified by the key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByRelPrevIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelPrevIdxKey argKey );
	/**
	 *	Delete the RelationCol instances identified by the key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the RelationCol instances identified by the key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteRelationColByRelNextIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelNextIdxKey argKey );


	/**
	 *	Read the derived RelationCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamRelationCol readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived RelationCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamRelationCol lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all RelationCol instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived RelationCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamRelationCol readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the derived RelationCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamRelationCol readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		String Name );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 FromColId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ToColId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamRelationCol[] readDerivedByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read the specific RelationCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific RelationCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific RelationCol record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific RelationCol instances in the database accessible for the Authorization.
	 */
	ICFBamRelationCol[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific RelationCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the specific RelationCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		String Name );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 FromColId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ToColId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamRelationCol[] readRecByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamRelationCol moveRecUp( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );

	/**
	 *	Move the specified record down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamRelationCol moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );
}
