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

public interface ICFBamUuid6ColTableObj
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
	 *	Instantiate a new Uuid6Col instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUuid6ColObj newInstance();

	/**
	 *	Instantiate a new Uuid6Col edition of the specified Uuid6Col instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUuid6ColEditObj newEditInstance( ICFBamUuid6ColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUuid6ColObj realiseUuid6Col( ICFBamUuid6ColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUuid6ColObj createUuid6Col( ICFBamUuid6ColObj Obj );

	/**
	 *	Read a Uuid6Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Uuid6Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuid6ColObj readUuid6Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Uuid6Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Uuid6Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuid6ColObj readUuid6Col( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamUuid6ColObj readCachedUuid6Col( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeUuid6Col( ICFBamUuid6ColObj obj );

	void deepDisposeUuid6Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUuid6ColObj lockUuid6Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Uuid6Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuid6ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuid6ColObj> readAllUuid6Col();

	/**
	 *	Return a sorted map of all the Uuid6Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuid6ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuid6ColObj> readAllUuid6Col( boolean forceRead );

	List<ICFBamUuid6ColObj> readCachedAllUuid6Col();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuid6ColObj readUuid6ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuid6ColObj readUuid6ColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuid6ColObj readUuid6ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuid6ColObj readUuid6ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamUuid6ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuid6ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuid6ColObj> readUuid6ColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamUuid6ColObj readCachedUuid6ColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamUuid6ColObj readCachedUuid6ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamUuid6ColObj> readCachedUuid6ColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeUuid6ColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeUuid6ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeUuid6ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeUuid6ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeUuid6ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeUuid6ColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeUuid6ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeUuid6ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeUuid6ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamUuid6ColObj updateUuid6Col( ICFBamUuid6ColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUuid6Col( ICFBamUuid6ColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Uuid6Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Uuid6Col key attribute of the instance generating the id.
	 */
	void deleteUuid6ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamUuid6ColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuid6ColObj refreshed cache instance.
	 */
	ICFBamUuid6ColObj moveUpUuid6Col( ICFBamUuid6ColObj Obj );

	/**
	 *	Move the CFBamUuid6ColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuid6ColObj refreshed cache instance.
	 */
	ICFBamUuid6ColObj moveDownUuid6Col( ICFBamUuid6ColObj Obj );
}
