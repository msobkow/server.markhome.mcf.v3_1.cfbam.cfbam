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

public interface ICFBamBlobColTableObj
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
	 *	Instantiate a new BlobCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamBlobColObj newInstance();

	/**
	 *	Instantiate a new BlobCol edition of the specified BlobCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamBlobColEditObj newEditInstance( ICFBamBlobColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobColObj realiseBlobCol( ICFBamBlobColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobColObj createBlobCol( ICFBamBlobColObj Obj );

	/**
	 *	Read a BlobCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobColObj readBlobCol( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a BlobCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobColObj readBlobCol( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFBamBlobColObj readCachedBlobCol( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeBlobCol( ICFBamBlobColObj obj );

	void deepDisposeBlobCol( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobColObj lockBlobCol( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the BlobCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobColObj> readAllBlobCol();

	/**
	 *	Return a sorted map of all the BlobCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobColObj> readAllBlobCol( boolean forceRead );

	List<ICFBamBlobColObj> readCachedAllBlobCol();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobColObj readBlobColByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobColObj readBlobColByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobColObj readBlobColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobColObj readBlobColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Get the map of CFBamBlobColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobColObj> readBlobColByTableIdx( ICFLibKeyHash256 TableId,
		boolean forceRead );

	ICFBamBlobColObj readCachedBlobColByIdIdx( ICFLibKeyHash256 Id );

	ICFBamBlobColObj readCachedBlobColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamBlobColObj> readCachedBlobColByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamBlobColObj> readCachedBlobColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamBlobColObj> readCachedBlobColByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamBlobColObj> readCachedBlobColByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamBlobColObj> readCachedBlobColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamBlobColObj> readCachedBlobColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamBlobColObj> readCachedBlobColByTableIdx( ICFLibKeyHash256 TableId );

	void deepDisposeBlobColByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeBlobColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeBlobColByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeBlobColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeBlobColByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeBlobColByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeBlobColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeBlobColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeBlobColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobColObj updateBlobCol( ICFBamBlobColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteBlobCol( ICFBamBlobColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The BlobCol key attribute of the instance generating the id.
	 */
	void deleteBlobColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Move the CFBamBlobColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobColObj refreshed cache instance.
	 */
	ICFBamBlobColObj moveUpBlobCol( ICFBamBlobColObj Obj );

	/**
	 *	Move the CFBamBlobColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobColObj refreshed cache instance.
	 */
	ICFBamBlobColObj moveDownBlobCol( ICFBamBlobColObj Obj );
}
