// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamDbKeyHash128TypeTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new DbKeyHash128Type instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDbKeyHash128TypeObj newInstance();

	/**
	 *	Instantiate a new DbKeyHash128Type edition of the specified DbKeyHash128Type instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDbKeyHash128TypeEditObj newEditInstance( ICFBamDbKeyHash128TypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash128TypeObj realiseDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash128TypeObj createDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );

	/**
	 *	Read a DbKeyHash128Type-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash128Type-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DbKeyHash128Type-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash128Type-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128Type( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDbKeyHash128TypeObj readCachedDbKeyHash128Type( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDbKeyHash128Type( ICFBamDbKeyHash128TypeObj obj );

	void deepDisposeDbKeyHash128Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash128TypeObj lockDbKeyHash128Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DbKeyHash128Type-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash128TypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readAllDbKeyHash128Type();

	/**
	 *	Return a sorted map of all the DbKeyHash128Type-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash128TypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readAllDbKeyHash128Type( boolean forceRead );

	List<ICFBamDbKeyHash128TypeObj> readCachedAllDbKeyHash128Type();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128TypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128TypeByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash128TypeObj readDbKeyHash128TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamDbKeyHash128TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash128TypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash128TypeObj> readDbKeyHash128TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamDbKeyHash128TypeObj readCachedDbKeyHash128TypeByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDbKeyHash128TypeObj readCachedDbKeyHash128TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash128TypeObj> readCachedDbKeyHash128TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeDbKeyHash128TypeByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDbKeyHash128TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDbKeyHash128TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDbKeyHash128TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDbKeyHash128TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash128TypeByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash128TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash128TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash128TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash128TypeObj updateDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash128Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The DbKeyHash128Type key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash128TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamDbKeyHash128TypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash128TypeObj refreshed cache instance.
	 */
	ICFBamDbKeyHash128TypeObj moveUpDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );

	/**
	 *	Move the CFBamDbKeyHash128TypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash128TypeObj refreshed cache instance.
	 */
	ICFBamDbKeyHash128TypeObj moveDownDbKeyHash128Type( ICFBamDbKeyHash128TypeObj Obj );
}
