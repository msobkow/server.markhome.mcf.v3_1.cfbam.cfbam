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

public interface ICFBamIndexColTableObj
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
	 *	Instantiate a new IndexCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamIndexColObj newInstance();

	/**
	 *	Instantiate a new IndexCol edition of the specified IndexCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamIndexColEditObj newEditInstance( ICFBamIndexColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamIndexColObj realiseIndexCol( ICFBamIndexColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamIndexColObj createIndexCol( ICFBamIndexColObj Obj );

	/**
	 *	Read a IndexCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The IndexCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamIndexColObj readIndexCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a IndexCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The IndexCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamIndexColObj readIndexCol( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamIndexColObj readCachedIndexCol( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeIndexCol( ICFBamIndexColObj obj );

	void deepDisposeIndexCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamIndexColObj lockIndexCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the IndexCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamIndexColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamIndexColObj> readAllIndexCol();

	/**
	 *	Return a sorted map of all the IndexCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamIndexColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamIndexColObj> readAllIndexCol( boolean forceRead );

	List<ICFBamIndexColObj> readCachedAllIndexCol();

	/**
	 *	Get the CFBamIndexColObj instance for the primary key attributes.
	 *
	 *	@param	Id	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamIndexColObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamIndexColObj readIndexColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamIndexColObj instance for the primary key attributes.
	 *
	 *	@param	Id	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamIndexColObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamIndexColObj readIndexColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamIndexColObj instance for the unique UNameIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamIndexColObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamIndexColObj readIndexColByUNameIdx(CFLibDbKeyHash256 IndexId,
		String Name );

	/**
	 *	Get the CFBamIndexColObj instance for the unique UNameIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamIndexColObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamIndexColObj readIndexColByUNameIdx(CFLibDbKeyHash256 IndexId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IndexIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IndexIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIndexIdx( CFLibDbKeyHash256 IndexId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IndexIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IndexIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIndexIdx( CFLibDbKeyHash256 IndexId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate ColIdx key.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate ColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByColIdx( CFLibDbKeyHash256 ColumnId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate ColIdx key.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate ColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByColIdx( CFLibDbKeyHash256 ColumnId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IdxPrevIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IdxPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IdxPrevIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IdxPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IdxNextIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IdxNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamIndexColObj instances sorted by their primary keys for the duplicate IdxNextIdx key.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamIndexColObj cached instances sorted by their primary keys for the duplicate IdxNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamIndexColObj> readIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamIndexColObj readCachedIndexColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamIndexColObj readCachedIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name );

	List<ICFBamIndexColObj> readCachedIndexColByIndexIdx( CFLibDbKeyHash256 IndexId );

	List<ICFBamIndexColObj> readCachedIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamIndexColObj> readCachedIndexColByColIdx( CFLibDbKeyHash256 ColumnId );

	List<ICFBamIndexColObj> readCachedIndexColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamIndexColObj> readCachedIndexColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamIndexColObj> readCachedIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamIndexColObj> readCachedIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeIndexColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeIndexColByUNameIdx( CFLibDbKeyHash256 IndexId,
		String Name );

	void deepDisposeIndexColByIndexIdx( CFLibDbKeyHash256 IndexId );

	void deepDisposeIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeIndexColByColIdx( CFLibDbKeyHash256 ColumnId );

	void deepDisposeIndexColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeIndexColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamIndexColObj updateIndexCol( ICFBamIndexColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteIndexCol( ICFBamIndexColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByUNameIdx(CFLibDbKeyHash256 IndexId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByIndexIdx( CFLibDbKeyHash256 IndexId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByColIdx( CFLibDbKeyHash256 ColumnId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByIdxPrevIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 */
	void deleteIndexColByIdxNextIdx( CFLibDbKeyHash256 IndexId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamIndexColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamIndexColObj refreshed cache instance.
	 */
	ICFBamIndexColObj moveUpIndexCol( ICFBamIndexColObj Obj );

	/**
	 *	Move the CFBamIndexColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamIndexColObj refreshed cache instance.
	 */
	ICFBamIndexColObj moveDownIndexCol( ICFBamIndexColObj Obj );
}
