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

public interface ICFBamUuidColTableObj
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
	 *	Instantiate a new UuidCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUuidColObj newInstance();

	/**
	 *	Instantiate a new UuidCol edition of the specified UuidCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUuidColEditObj newEditInstance( ICFBamUuidColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidColObj realiseUuidCol( ICFBamUuidColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidColObj createUuidCol( ICFBamUuidColObj Obj );

	/**
	 *	Read a UuidCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UuidCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuidColObj readUuidCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a UuidCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UuidCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuidColObj readUuidCol( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamUuidColObj readCachedUuidCol( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeUuidCol( ICFBamUuidColObj obj );

	void deepDisposeUuidCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidColObj lockUuidCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the UuidCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuidColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuidColObj> readAllUuidCol();

	/**
	 *	Return a sorted map of all the UuidCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuidColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuidColObj> readAllUuidCol( boolean forceRead );

	List<ICFBamUuidColObj> readCachedAllUuidCol();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidColObj readUuidColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidColObj readUuidColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidColObj readUuidColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidColObj readUuidColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamUuidColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidColObj> readUuidColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamUuidColObj readCachedUuidColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamUuidColObj readCachedUuidColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamUuidColObj> readCachedUuidColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamUuidColObj> readCachedUuidColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamUuidColObj> readCachedUuidColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamUuidColObj> readCachedUuidColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamUuidColObj> readCachedUuidColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamUuidColObj> readCachedUuidColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamUuidColObj> readCachedUuidColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeUuidColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeUuidColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeUuidColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeUuidColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeUuidColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeUuidColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeUuidColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeUuidColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeUuidColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidColObj updateUuidCol( ICFBamUuidColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUuidCol( ICFBamUuidColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The UuidCol key attribute of the instance generating the id.
	 */
	void deleteUuidColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamUuidColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuidColObj refreshed cache instance.
	 */
	ICFBamUuidColObj moveUpUuidCol( ICFBamUuidColObj Obj );

	/**
	 *	Move the CFBamUuidColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuidColObj refreshed cache instance.
	 */
	ICFBamUuidColObj moveDownUuidCol( ICFBamUuidColObj Obj );
}
