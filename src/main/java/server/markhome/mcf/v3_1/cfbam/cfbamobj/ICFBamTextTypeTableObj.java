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

public interface ICFBamTextTypeTableObj
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
	 *	Instantiate a new TextType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTextTypeObj newInstance();

	/**
	 *	Instantiate a new TextType edition of the specified TextType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTextTypeEditObj newEditInstance( ICFBamTextTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj realiseTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj createTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Read a TextType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TextType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTextTypeObj readTextType( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a TextType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TextType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTextTypeObj readTextType( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamTextTypeObj readCachedTextType( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeTextType( ICFBamTextTypeObj obj );

	void deepDisposeTextType( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj lockTextType( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TextType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTextTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTextTypeObj> readAllTextType();

	/**
	 *	Return a sorted map of all the TextType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTextTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTextTypeObj> readAllTextType( boolean forceRead );

	List<ICFBamTextTypeObj> readCachedAllTextType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamTextTypeObj readCachedTextTypeByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamTextTypeObj readCachedTextTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamTextTypeObj> readCachedTextTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamTextTypeObj> readCachedTextTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamTextTypeObj> readCachedTextTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamTextTypeObj> readCachedTextTypeByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamTextTypeObj> readCachedTextTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamTextTypeObj> readCachedTextTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamTextTypeObj> readCachedTextTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeTextTypeByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeTextTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeTextTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeTextTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeTextTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeTextTypeByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeTextTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeTextTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeTextTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj updateTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamTextTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTextTypeObj refreshed cache instance.
	 */
	ICFBamTextTypeObj moveUpTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Move the CFBamTextTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTextTypeObj refreshed cache instance.
	 */
	ICFBamTextTypeObj moveDownTextType( ICFBamTextTypeObj Obj );
}
