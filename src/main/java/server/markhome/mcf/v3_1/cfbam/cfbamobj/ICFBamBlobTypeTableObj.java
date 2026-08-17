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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public interface ICFBamBlobTypeTableObj
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
	 *	Instantiate a new BlobType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamBlobTypeObj newInstance();

	/**
	 *	Instantiate a new BlobType edition of the specified BlobType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamBlobTypeEditObj newEditInstance( ICFBamBlobTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobTypeObj realiseBlobType( ICFBamBlobTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobTypeObj createBlobType( ICFBamBlobTypeObj Obj );

	/**
	 *	Read a BlobType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobTypeObj readBlobType( ICFLibKeyHash256 pkey );

	/**
	 *	Read a BlobType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobTypeObj readBlobType( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamBlobTypeObj readCachedBlobType( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeBlobType( ICFBamBlobTypeObj obj );

	void deepDisposeBlobType( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobTypeObj lockBlobType( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the BlobType-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobTypeObj> readAllBlobType();

	/**
	 *	Return a sorted map of all the BlobType-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobTypeObj> readAllBlobType( boolean forceRead );

	List<ICFBamBlobTypeObj> readCachedAllBlobType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobTypeObj readBlobTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobTypeObj readBlobTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobTypeObj readBlobTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobTypeObj readBlobTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamBlobTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobTypeObj> readBlobTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamBlobTypeObj readCachedBlobTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamBlobTypeObj readCachedBlobTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamBlobTypeObj> readCachedBlobTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeBlobTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeBlobTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeBlobTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeBlobTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeBlobTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeBlobTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeBlobTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeBlobTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeBlobTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobTypeObj updateBlobType( ICFBamBlobTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteBlobType( ICFBamBlobTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The BlobType key attribute of the instance generating the id.
	 */
	void deleteBlobTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamBlobTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobTypeObj refreshed cache instance.
	 */
	ICFBamBlobTypeObj moveUpBlobType( ICFBamBlobTypeObj Obj );

	/**
	 *	Move the CFBamBlobTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobTypeObj refreshed cache instance.
	 */
	ICFBamBlobTypeObj moveDownBlobType( ICFBamBlobTypeObj Obj );
}
