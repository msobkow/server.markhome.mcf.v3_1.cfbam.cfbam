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

public interface ICFBamInt32DefTableObj
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
	 *	Instantiate a new Int32Def instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamInt32DefObj newInstance();

	/**
	 *	Instantiate a new Int32Def edition of the specified Int32Def instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamInt32DefEditObj newEditInstance( ICFBamInt32DefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32DefObj realiseInt32Def( ICFBamInt32DefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32DefObj createInt32Def( ICFBamInt32DefObj Obj );

	/**
	 *	Read a Int32Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int32Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt32DefObj readInt32Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Int32Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int32Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt32DefObj readInt32Def( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamInt32DefObj readCachedInt32Def( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeInt32Def( ICFBamInt32DefObj obj );

	void deepDisposeInt32Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32DefObj lockInt32Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Int32Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt32DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt32DefObj> readAllInt32Def();

	/**
	 *	Return a sorted map of all the Int32Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt32DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt32DefObj> readAllInt32Def( boolean forceRead );

	List<ICFBamInt32DefObj> readCachedAllInt32Def();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32DefObj readInt32DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32DefObj readInt32DefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32DefObj readInt32DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32DefObj readInt32DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt32DefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32DefObj> readInt32DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamInt32DefObj readCachedInt32DefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamInt32DefObj readCachedInt32DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamInt32DefObj> readCachedInt32DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamInt32DefObj> readCachedInt32DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamInt32DefObj> readCachedInt32DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamInt32DefObj> readCachedInt32DefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamInt32DefObj> readCachedInt32DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamInt32DefObj> readCachedInt32DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeInt32DefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeInt32DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeInt32DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeInt32DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeInt32DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeInt32DefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeInt32DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeInt32DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32DefObj updateInt32Def( ICFBamInt32DefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteInt32Def( ICFBamInt32DefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Def key attribute of the instance generating the id.
	 */
	void deleteInt32DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamInt32DefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt32DefObj refreshed cache instance.
	 */
	ICFBamInt32DefObj moveUpInt32Def( ICFBamInt32DefObj Obj );

	/**
	 *	Move the CFBamInt32DefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt32DefObj refreshed cache instance.
	 */
	ICFBamInt32DefObj moveDownInt32Def( ICFBamInt32DefObj Obj );
}
