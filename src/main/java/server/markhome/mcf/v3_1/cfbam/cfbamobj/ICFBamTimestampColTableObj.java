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

public interface ICFBamTimestampColTableObj
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
	 *	Instantiate a new TimestampCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTimestampColObj newInstance();

	/**
	 *	Instantiate a new TimestampCol edition of the specified TimestampCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTimestampColEditObj newEditInstance( ICFBamTimestampColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampColObj realiseTimestampCol( ICFBamTimestampColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampColObj createTimestampCol( ICFBamTimestampColObj Obj );

	/**
	 *	Read a TimestampCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampColObj readTimestampCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a TimestampCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampColObj readTimestampCol( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamTimestampColObj readCachedTimestampCol( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeTimestampCol( ICFBamTimestampColObj obj );

	void deepDisposeTimestampCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampColObj lockTimestampCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TimestampCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampColObj> readAllTimestampCol();

	/**
	 *	Return a sorted map of all the TimestampCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampColObj> readAllTimestampCol( boolean forceRead );

	List<ICFBamTimestampColObj> readCachedAllTimestampCol();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampColObj readTimestampColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampColObj readTimestampColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampColObj readTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampColObj readTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamTimestampColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampColObj> readTimestampColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamTimestampColObj readCachedTimestampColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamTimestampColObj readCachedTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamTimestampColObj> readCachedTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamTimestampColObj> readCachedTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamTimestampColObj> readCachedTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamTimestampColObj> readCachedTimestampColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamTimestampColObj> readCachedTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamTimestampColObj> readCachedTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamTimestampColObj> readCachedTimestampColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeTimestampColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeTimestampColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeTimestampColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampColObj updateTimestampCol( ICFBamTimestampColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTimestampCol( ICFBamTimestampColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The TimestampCol key attribute of the instance generating the id.
	 */
	void deleteTimestampColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamTimestampColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampColObj refreshed cache instance.
	 */
	ICFBamTimestampColObj moveUpTimestampCol( ICFBamTimestampColObj Obj );

	/**
	 *	Move the CFBamTimestampColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampColObj refreshed cache instance.
	 */
	ICFBamTimestampColObj moveDownTimestampCol( ICFBamTimestampColObj Obj );
}
