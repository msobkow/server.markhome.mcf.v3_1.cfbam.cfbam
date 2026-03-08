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

public interface ICFBamEnumTagTableObj
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
	 *	Instantiate a new EnumTag instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamEnumTagObj newInstance();

	/**
	 *	Instantiate a new EnumTag edition of the specified EnumTag instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamEnumTagEditObj newEditInstance( ICFBamEnumTagObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamEnumTagObj realiseEnumTag( ICFBamEnumTagObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamEnumTagObj createEnumTag( ICFBamEnumTagObj Obj );

	/**
	 *	Read a EnumTag-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The EnumTag-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamEnumTagObj readEnumTag( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a EnumTag-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The EnumTag-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamEnumTagObj readEnumTag( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamEnumTagObj readCachedEnumTag( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeEnumTag( ICFBamEnumTagObj obj );

	void deepDisposeEnumTag( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamEnumTagObj lockEnumTag( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the EnumTag-derived instances in the database.
	 *
	 *	@return	List of ICFBamEnumTagObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamEnumTagObj> readAllEnumTag();

	/**
	 *	Return a sorted map of all the EnumTag-derived instances in the database.
	 *
	 *	@return	List of ICFBamEnumTagObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamEnumTagObj> readAllEnumTag( boolean forceRead );

	List<ICFBamEnumTagObj> readCachedAllEnumTag();

	/**
	 *	Get the CFBamEnumTagObj instance for the primary key attributes.
	 *
	 *	@param	Id	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	CFBamEnumTagObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamEnumTagObj readEnumTagByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamEnumTagObj instance for the primary key attributes.
	 *
	 *	@param	Id	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	CFBamEnumTagObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamEnumTagObj readEnumTagByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate EnumIdx key.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate EnumIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate EnumIdx key.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate EnumIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the CFBamEnumTagObj instance for the unique EnumNameIdx key.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	CFBamEnumTagObj cached instance for the unique EnumNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamEnumTagObj readEnumTagByEnumNameIdx(CFLibDbKeyHash256 EnumId,
		String Name );

	/**
	 *	Get the CFBamEnumTagObj instance for the unique EnumNameIdx key.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	CFBamEnumTagObj refreshed instance for the unique EnumNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamEnumTagObj readEnumTagByEnumNameIdx(CFLibDbKeyHash256 EnumId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamEnumTagObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamEnumTagObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamEnumTagObj> readEnumTagByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamEnumTagObj readCachedEnumTagByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamEnumTagObj> readCachedEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId );

	List<ICFBamEnumTagObj> readCachedEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	ICFBamEnumTagObj readCachedEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name );

	List<ICFBamEnumTagObj> readCachedEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamEnumTagObj> readCachedEnumTagByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeEnumTagByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId );

	void deepDisposeEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name );

	void deepDisposeEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeEnumTagByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamEnumTagObj updateEnumTag( ICFBamEnumTagObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteEnumTag( ICFBamEnumTagObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByEnumNameIdx(CFLibDbKeyHash256 EnumId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 */
	void deleteEnumTagByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamEnumTagObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamEnumTagObj refreshed cache instance.
	 */
	ICFBamEnumTagObj moveUpEnumTag( ICFBamEnumTagObj Obj );

	/**
	 *	Move the CFBamEnumTagObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamEnumTagObj refreshed cache instance.
	 */
	ICFBamEnumTagObj moveDownEnumTag( ICFBamEnumTagObj Obj );
}
