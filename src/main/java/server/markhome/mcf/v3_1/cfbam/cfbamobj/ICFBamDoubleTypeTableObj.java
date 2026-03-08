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

public interface ICFBamDoubleTypeTableObj
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
	 *	Instantiate a new DoubleType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDoubleTypeObj newInstance();

	/**
	 *	Instantiate a new DoubleType edition of the specified DoubleType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDoubleTypeEditObj newEditInstance( ICFBamDoubleTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj realiseDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj createDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Read a DoubleType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DoubleType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDoubleTypeObj readDoubleType( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DoubleType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DoubleType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDoubleTypeObj readDoubleType( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDoubleTypeObj readCachedDoubleType( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDoubleType( ICFBamDoubleTypeObj obj );

	void deepDisposeDoubleType( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj lockDoubleType( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DoubleType-derived instances in the database.
	 *
	 *	@return	List of ICFBamDoubleTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDoubleTypeObj> readAllDoubleType();

	/**
	 *	Return a sorted map of all the DoubleType-derived instances in the database.
	 *
	 *	@return	List of ICFBamDoubleTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDoubleTypeObj> readAllDoubleType( boolean forceRead );

	List<ICFBamDoubleTypeObj> readCachedAllDoubleType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamDoubleTypeObj readCachedDoubleTypeByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDoubleTypeObj readCachedDoubleTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeDoubleTypeByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDoubleTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDoubleTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDoubleTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDoubleTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDoubleTypeByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDoubleTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDoubleTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDoubleTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj updateDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamDoubleTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDoubleTypeObj refreshed cache instance.
	 */
	ICFBamDoubleTypeObj moveUpDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Move the CFBamDoubleTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDoubleTypeObj refreshed cache instance.
	 */
	ICFBamDoubleTypeObj moveDownDoubleType( ICFBamDoubleTypeObj Obj );
}
