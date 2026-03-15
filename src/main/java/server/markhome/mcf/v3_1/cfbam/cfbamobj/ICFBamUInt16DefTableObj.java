// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public interface ICFBamUInt16DefTableObj
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
	 *	Instantiate a new UInt16Def instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUInt16DefObj newInstance();

	/**
	 *	Instantiate a new UInt16Def edition of the specified UInt16Def instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUInt16DefEditObj newEditInstance( ICFBamUInt16DefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt16DefObj realiseUInt16Def( ICFBamUInt16DefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt16DefObj createUInt16Def( ICFBamUInt16DefObj Obj );

	/**
	 *	Read a UInt16Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt16Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt16DefObj readUInt16Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a UInt16Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UInt16Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUInt16DefObj readUInt16Def( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamUInt16DefObj readCachedUInt16Def( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeUInt16Def( ICFBamUInt16DefObj obj );

	void deepDisposeUInt16Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt16DefObj lockUInt16Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the UInt16Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt16DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt16DefObj> readAllUInt16Def();

	/**
	 *	Return a sorted map of all the UInt16Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamUInt16DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUInt16DefObj> readAllUInt16Def( boolean forceRead );

	List<ICFBamUInt16DefObj> readCachedAllUInt16Def();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt16DefObj readUInt16DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt16DefObj readUInt16DefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt16DefObj readUInt16DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUInt16DefObj readUInt16DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUInt16DefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUInt16DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUInt16DefObj> readUInt16DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamUInt16DefObj readCachedUInt16DefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamUInt16DefObj readCachedUInt16DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamUInt16DefObj> readCachedUInt16DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeUInt16DefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeUInt16DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeUInt16DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeUInt16DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeUInt16DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt16DefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeUInt16DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeUInt16DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamUInt16DefObj updateUInt16Def( ICFBamUInt16DefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUInt16Def( ICFBamUInt16DefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UInt16Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UInt16Def key attribute of the instance generating the id.
	 */
	void deleteUInt16DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamUInt16DefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt16DefObj refreshed cache instance.
	 */
	ICFBamUInt16DefObj moveUpUInt16Def( ICFBamUInt16DefObj Obj );

	/**
	 *	Move the CFBamUInt16DefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUInt16DefObj refreshed cache instance.
	 */
	ICFBamUInt16DefObj moveDownUInt16Def( ICFBamUInt16DefObj Obj );
}
