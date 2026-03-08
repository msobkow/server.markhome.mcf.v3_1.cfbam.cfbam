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

public interface ICFBamUInt32TypeTableObj
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
	 *	Instantiate a new UInt32Type instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUInt32TypeObj newInstance();

	/**
	 *	Instantiate a new UInt32Type edition of the specified UInt32Type instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUInt32TypeEditObj newEditInstance( ICFBamUInt32TypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt32TypeObj realiseUInt32Type( ICFBamUInt32TypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt32TypeObj createUInt32Type( ICFBamUInt32TypeObj Obj );

	/**
	 *	Read a UInt32Type-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt32Type-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt32TypeObj readUInt32Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a UInt32Type-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt32Type-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt32TypeObj readUInt32Type( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamUInt32TypeObj readCachedUInt32Type( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeUInt32Type( ICFBamUInt32TypeObj obj );

	void deepDisposeUInt32Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt32TypeObj lockUInt32Type( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the UInt32Type-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt32TypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt32TypeObj> readAllUInt32Type();

	/**
	 *	Return a sorted map of all the UInt32Type-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt32TypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt32TypeObj> readAllUInt32Type( boolean forceRead );

	List<ICFBamUInt32TypeObj> readCachedAllUInt32Type();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt32TypeObj readUInt32TypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt32TypeObj readUInt32TypeByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt32TypeObj readUInt32TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt32TypeObj readUInt32TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamUInt32TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt32TypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt32TypeObj> readUInt32TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamUInt32TypeObj readCachedUInt32TypeByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamUInt32TypeObj readCachedUInt32TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamUInt32TypeObj> readCachedUInt32TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeUInt32TypeByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeUInt32TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeUInt32TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeUInt32TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeUInt32TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt32TypeByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeUInt32TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt32TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeUInt32TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt32TypeObj updateUInt32Type( ICFBamUInt32TypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUInt32Type( ICFBamUInt32TypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt32Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The UInt32Type key attribute of the instance generating the id.
	 */
	void deleteUInt32TypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamUInt32TypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt32TypeObj refreshed cache instance.
	 */
	ICFBamUInt32TypeObj moveUpUInt32Type( ICFBamUInt32TypeObj Obj );

	/**
	 *	Move the CFBamUInt32TypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt32TypeObj refreshed cache instance.
	 */
	ICFBamUInt32TypeObj moveDownUInt32Type( ICFBamUInt32TypeObj Obj );
}
