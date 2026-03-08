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

public interface ICFBamDbKeyHash160ColTableObj
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
	 *	Instantiate a new DbKeyHash160Col instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDbKeyHash160ColObj newInstance();

	/**
	 *	Instantiate a new DbKeyHash160Col edition of the specified DbKeyHash160Col instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDbKeyHash160ColEditObj newEditInstance( ICFBamDbKeyHash160ColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash160ColObj realiseDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash160ColObj createDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );

	/**
	 *	Read a DbKeyHash160Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash160Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DbKeyHash160Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash160Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160Col( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDbKeyHash160ColObj readCachedDbKeyHash160Col( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDbKeyHash160Col( ICFBamDbKeyHash160ColObj obj );

	void deepDisposeDbKeyHash160Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash160ColObj lockDbKeyHash160Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DbKeyHash160Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash160ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readAllDbKeyHash160Col();

	/**
	 *	Return a sorted map of all the DbKeyHash160Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash160ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readAllDbKeyHash160Col( boolean forceRead );

	List<ICFBamDbKeyHash160ColObj> readCachedAllDbKeyHash160Col();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash160ColObj readDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamDbKeyHash160ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash160ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash160ColObj> readDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamDbKeyHash160ColObj readCachedDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDbKeyHash160ColObj readCachedDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash160ColObj> readCachedDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash160ColObj updateDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash160ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamDbKeyHash160ColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash160ColObj refreshed cache instance.
	 */
	ICFBamDbKeyHash160ColObj moveUpDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );

	/**
	 *	Move the CFBamDbKeyHash160ColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash160ColObj refreshed cache instance.
	 */
	ICFBamDbKeyHash160ColObj moveDownDbKeyHash160Col( ICFBamDbKeyHash160ColObj Obj );
}
