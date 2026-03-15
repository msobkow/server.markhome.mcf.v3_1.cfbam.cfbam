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

public interface ICFBamChainTableObj
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
	 *	Instantiate a new Chain instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamChainObj newInstance();

	/**
	 *	Instantiate a new Chain edition of the specified Chain instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamChainEditObj newEditInstance( ICFBamChainObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamChainObj realiseChain( ICFBamChainObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamChainObj createChain( ICFBamChainObj Obj );

	/**
	 *	Read a Chain-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Chain-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamChainObj readChain( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Chain-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Chain-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamChainObj readChain( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamChainObj readCachedChain( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeChain( ICFBamChainObj obj );

	void deepDisposeChain( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamChainObj lockChain( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Chain-derived instances in the database.
	 *
	 *	@return	List of ICFBamChainObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamChainObj> readAllChain();

	/**
	 *	Return a sorted map of all the Chain-derived instances in the database.
	 *
	 *	@return	List of ICFBamChainObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamChainObj> readAllChain( boolean forceRead );

	List<ICFBamChainObj> readCachedAllChain();

	/**
	 *	Get the CFBamChainObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	CFBamChainObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamChainObj readChainByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamChainObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	CFBamChainObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamChainObj readChainByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate ChainTableIdx key.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate ChainTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByChainTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate ChainTableIdx key.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate ChainTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByChainTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the CFBamChainObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	CFBamChainObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamChainObj readChainByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamChainObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	CFBamChainObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamChainObj readChainByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate PrevRelIdx key.
	 *
	 *	@param	PrevRelationId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate PrevRelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate PrevRelIdx key.
	 *
	 *	@param	PrevRelationId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate PrevRelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate NextRelIdx key.
	 *
	 *	@param	NextRelationId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate NextRelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId );

	/**
	 *	Get the map of CFBamChainObj instances sorted by their primary keys for the duplicate NextRelIdx key.
	 *
	 *	@param	NextRelationId	The Chain key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamChainObj cached instances sorted by their primary keys for the duplicate NextRelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamChainObj> readChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId,
		boolean forceRead );

	ICFBamChainObj readCachedChainByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamChainObj> readCachedChainByChainTableIdx( CFLibDbKeyHash256 TableId );

	List<ICFBamChainObj> readCachedChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	ICFBamChainObj readCachedChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	List<ICFBamChainObj> readCachedChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId );

	List<ICFBamChainObj> readCachedChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId );

	void deepDisposeChainByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeChainByChainTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	void deepDisposeChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId );

	void deepDisposeChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId );

	/**
	 *	Internal use only.
	 */
	ICFBamChainObj updateChain( ICFBamChainObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteChain( ICFBamChainObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByChainTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Chain key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevRelationId	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextRelationId	The Chain key attribute of the instance generating the id.
	 */
	void deleteChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId );
}
