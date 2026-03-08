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

public interface ICFBamFloatDefTableObj
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
	 *	Instantiate a new FloatDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamFloatDefObj newInstance();

	/**
	 *	Instantiate a new FloatDef edition of the specified FloatDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamFloatDefEditObj newEditInstance( ICFBamFloatDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamFloatDefObj realiseFloatDef( ICFBamFloatDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamFloatDefObj createFloatDef( ICFBamFloatDefObj Obj );

	/**
	 *	Read a FloatDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The FloatDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamFloatDefObj readFloatDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a FloatDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The FloatDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamFloatDefObj readFloatDef( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamFloatDefObj readCachedFloatDef( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeFloatDef( ICFBamFloatDefObj obj );

	void deepDisposeFloatDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamFloatDefObj lockFloatDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the FloatDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamFloatDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamFloatDefObj> readAllFloatDef();

	/**
	 *	Return a sorted map of all the FloatDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamFloatDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamFloatDefObj> readAllFloatDef( boolean forceRead );

	List<ICFBamFloatDefObj> readCachedAllFloatDef();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamFloatDefObj readFloatDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamFloatDefObj readFloatDefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamFloatDefObj readFloatDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamFloatDefObj readFloatDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamFloatDefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamFloatDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamFloatDefObj> readFloatDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamFloatDefObj readCachedFloatDefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamFloatDefObj readCachedFloatDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamFloatDefObj> readCachedFloatDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamFloatDefObj> readCachedFloatDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamFloatDefObj> readCachedFloatDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamFloatDefObj> readCachedFloatDefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamFloatDefObj> readCachedFloatDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamFloatDefObj> readCachedFloatDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeFloatDefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeFloatDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeFloatDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeFloatDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeFloatDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeFloatDefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeFloatDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeFloatDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamFloatDefObj updateFloatDef( ICFBamFloatDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteFloatDef( ICFBamFloatDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The FloatDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The FloatDef key attribute of the instance generating the id.
	 */
	void deleteFloatDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamFloatDefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamFloatDefObj refreshed cache instance.
	 */
	ICFBamFloatDefObj moveUpFloatDef( ICFBamFloatDefObj Obj );

	/**
	 *	Move the CFBamFloatDefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamFloatDefObj refreshed cache instance.
	 */
	ICFBamFloatDefObj moveDownFloatDef( ICFBamFloatDefObj Obj );
}
