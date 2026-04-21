
// Description: Java 25 DbIO interface for StringType.

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
 *	CFBamStringTypeTable database interface for StringType
 */
public interface ICFBamStringTypeTable
{
	public static final String TABLE_NAME = "StringType";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFBamStringType createStringType( ICFSecAuthorization Authorization,
		ICFBamStringType rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFBamStringType updateStringType( ICFSecAuthorization Authorization,
		ICFBamStringType rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteStringType( ICFSecAuthorization Authorization,
		ICFBamStringType rec );
	/**
	 *	Delete the StringType instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId );

	/**
	 *	Delete the StringType instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeBySchemaIdx( ICFSecAuthorization Authorization,
		ICFBamStringTypeBySchemaIdxKey argKey );
	/**
	 *	Delete the StringType instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteStringTypeByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the StringType instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName );

	/**
	 *	Delete the StringType instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamValueByUNameIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId );

	/**
	 *	Delete the StringType instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByScopeIdx( ICFSecAuthorization Authorization,
		ICFBamValueByScopeIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId );

	/**
	 *	Delete the StringType instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamValueByDefSchemaIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the StringType instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByPrevIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the StringType instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByNextIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId );

	/**
	 *	Delete the StringType instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByContPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContPrevIdxKey argKey );
	/**
	 *	Delete the StringType instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 */
	void deleteStringTypeByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId );

	/**
	 *	Delete the StringType instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteStringTypeByContNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContNextIdxKey argKey );


	/**
	 *	Read the derived StringType record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the StringType instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamStringType readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived StringType record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the StringType instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamStringType lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all StringType instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived StringType record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The StringType key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamStringType readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the derived StringType record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The StringType key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFBamStringType readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the derived StringType record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFBamStringType[] readDerivedBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Read the specific StringType record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the StringType instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific StringType record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the StringType instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific StringType record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific StringType instances in the database accessible for the Authorization.
	 */
	ICFBamStringType[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific StringType record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The StringType key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id );

	/**
	 *	Read the specific StringType record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The StringType key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The StringType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Read an array of the specific StringType record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The StringType key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFBamStringType[] readRecBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamStringType moveRecUp( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );

	/**
	 *	Move the specified record down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	ICFBamStringType moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 Id,
		int revision );
}
