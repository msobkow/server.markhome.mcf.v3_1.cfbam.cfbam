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

public interface ICFBamUInt64DefTableObj
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
	 *	Instantiate a new UInt64Def instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUInt64DefObj newInstance();

	/**
	 *	Instantiate a new UInt64Def edition of the specified UInt64Def instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUInt64DefEditObj newEditInstance( ICFBamUInt64DefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt64DefObj realiseUInt64Def( ICFBamUInt64DefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt64DefObj createUInt64Def( ICFBamUInt64DefObj Obj );

	/**
	 *	Read a UInt64Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt64Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt64DefObj readUInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a UInt64Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt64Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt64DefObj readUInt64Def( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamUInt64DefObj readCachedUInt64Def( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeUInt64Def( ICFBamUInt64DefObj obj );

	void deepDisposeUInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt64DefObj lockUInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the UInt64Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt64DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt64DefObj> readAllUInt64Def();

	/**
	 *	Return a sorted map of all the UInt64Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt64DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt64DefObj> readAllUInt64Def( boolean forceRead );

	List<ICFBamUInt64DefObj> readCachedAllUInt64Def();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt64DefObj readUInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt64DefObj readUInt64DefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt64DefObj readUInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt64DefObj readUInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt64DefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt64DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt64DefObj> readUInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamUInt64DefObj readCachedUInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamUInt64DefObj readCachedUInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt64DefObj> readCachedUInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeUInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeUInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeUInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeUInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeUInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeUInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt64DefObj updateUInt64Def( ICFBamUInt64DefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUInt64Def( ICFBamUInt64DefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt64Def key attribute of the instance generating the id.
	 */
	void deleteUInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamUInt64DefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt64DefObj refreshed cache instance.
	 */
	ICFBamUInt64DefObj moveUpUInt64Def( ICFBamUInt64DefObj Obj );

	/**
	 *	Move the CFBamUInt64DefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt64DefObj refreshed cache instance.
	 */
	ICFBamUInt64DefObj moveDownUInt64Def( ICFBamUInt64DefObj Obj );
}
