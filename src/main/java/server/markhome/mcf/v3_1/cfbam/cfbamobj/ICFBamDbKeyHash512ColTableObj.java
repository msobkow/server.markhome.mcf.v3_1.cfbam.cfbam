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

public interface ICFBamDbKeyHash512ColTableObj
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
	 *	Instantiate a new DbKeyHash512Col instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDbKeyHash512ColObj newInstance();

	/**
	 *	Instantiate a new DbKeyHash512Col edition of the specified DbKeyHash512Col instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDbKeyHash512ColEditObj newEditInstance( ICFBamDbKeyHash512ColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512ColObj realiseDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512ColObj createDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );

	/**
	 *	Read a DbKeyHash512Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash512Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DbKeyHash512Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash512Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512Col( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDbKeyHash512ColObj readCachedDbKeyHash512Col( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDbKeyHash512Col( ICFBamDbKeyHash512ColObj obj );

	void deepDisposeDbKeyHash512Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512ColObj lockDbKeyHash512Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DbKeyHash512Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash512ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readAllDbKeyHash512Col();

	/**
	 *	Return a sorted map of all the DbKeyHash512Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash512ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readAllDbKeyHash512Col( boolean forceRead );

	List<ICFBamDbKeyHash512ColObj> readCachedAllDbKeyHash512Col();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512ColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512ColObj readDbKeyHash512ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamDbKeyHash512ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512ColObj> readDbKeyHash512ColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamDbKeyHash512ColObj readCachedDbKeyHash512ColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDbKeyHash512ColObj readCachedDbKeyHash512ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash512ColObj> readCachedDbKeyHash512ColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeDbKeyHash512ColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDbKeyHash512ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDbKeyHash512ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDbKeyHash512ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDbKeyHash512ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash512ColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash512ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash512ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash512ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512ColObj updateDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The DbKeyHash512Col key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamDbKeyHash512ColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512ColObj refreshed cache instance.
	 */
	ICFBamDbKeyHash512ColObj moveUpDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );

	/**
	 *	Move the CFBamDbKeyHash512ColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512ColObj refreshed cache instance.
	 */
	ICFBamDbKeyHash512ColObj moveDownDbKeyHash512Col( ICFBamDbKeyHash512ColObj Obj );
}
