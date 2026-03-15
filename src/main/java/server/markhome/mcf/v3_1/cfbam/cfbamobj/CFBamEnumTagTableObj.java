// Description: Java 25 Table Object implementation for EnumTag.

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

public class CFBamEnumTagTableObj
	implements ICFBamEnumTagTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamEnumTag.CLASS_CODE;
	protected static final int backingClassCode = ICFBamEnumTag.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamEnumTagObj> members;
	private Map<CFLibDbKeyHash256, ICFBamEnumTagObj> allEnumTag;
	private Map< ICFBamEnumTagByEnumIdxKey,
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj > > indexByEnumIdx;
	private Map< ICFBamEnumTagByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj > > indexByDefSchemaIdx;
	private Map< ICFBamEnumTagByEnumNameIdxKey,
		ICFBamEnumTagObj > indexByEnumNameIdx;
	private Map< ICFBamEnumTagByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj > > indexByPrevIdx;
	private Map< ICFBamEnumTagByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj > > indexByNextIdx;
	public static String TABLE_NAME = "EnumTag";
	public static String TABLE_DBNAME = "enum_tag";

	public CFBamEnumTagTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
		allEnumTag = null;
		indexByEnumIdx = null;
		indexByDefSchemaIdx = null;
		indexByEnumNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}

	public CFBamEnumTagTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
		allEnumTag = null;
		indexByEnumIdx = null;
		indexByDefSchemaIdx = null;
		indexByEnumNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamEnumTagTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamEnumTagTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allEnumTag = null;
		indexByEnumIdx = null;
		indexByDefSchemaIdx = null;
		indexByEnumNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		List<ICFBamEnumTagObj> toForget = new LinkedList<ICFBamEnumTagObj>();
		ICFBamEnumTagObj cur = null;
		Iterator<ICFBamEnumTagObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamEnumTagObj.
	 */
	@Override
	public ICFBamEnumTagObj newInstance() {
		ICFBamEnumTagObj inst = new CFBamEnumTagObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamEnumTagObj.
	 */
	@Override
	public ICFBamEnumTagEditObj newEditInstance( ICFBamEnumTagObj orig ) {
		ICFBamEnumTagEditObj edit = new CFBamEnumTagEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamEnumTagObj realiseEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamEnumTagObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamEnumTagObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByEnumIdx != null ) {
				ICFBamEnumTagByEnumIdxKey keyEnumIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
				keyEnumIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapEnumIdx = indexByEnumIdx.get( keyEnumIdx );
				if( mapEnumIdx != null ) {
					mapEnumIdx.remove( keepObj.getPKey() );
					if( mapEnumIdx.size() <= 0 ) {
						indexByEnumIdx.remove( keyEnumIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamEnumTagByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByEnumNameIdx != null ) {
				ICFBamEnumTagByEnumNameIdxKey keyEnumNameIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
				keyEnumNameIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				keyEnumNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByEnumNameIdx.remove( keyEnumNameIdx );
			}

			if( indexByPrevIdx != null ) {
				ICFBamEnumTagByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamEnumTagByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByEnumIdx != null ) {
				ICFBamEnumTagByEnumIdxKey keyEnumIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
				keyEnumIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapEnumIdx = indexByEnumIdx.get( keyEnumIdx );
				if( mapEnumIdx != null ) {
					mapEnumIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamEnumTagByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByEnumNameIdx != null ) {
				ICFBamEnumTagByEnumNameIdxKey keyEnumNameIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
				keyEnumNameIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				keyEnumNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByEnumNameIdx.put( keyEnumNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamEnumTagByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamEnumTagByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allEnumTag != null ) {
				allEnumTag.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allEnumTag != null ) {
				allEnumTag.put( keepObj.getPKey(), keepObj );
			}

			if( indexByEnumIdx != null ) {
				ICFBamEnumTagByEnumIdxKey keyEnumIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
				keyEnumIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapEnumIdx = indexByEnumIdx.get( keyEnumIdx );
				if( mapEnumIdx != null ) {
					mapEnumIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamEnumTagByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByEnumNameIdx != null ) {
				ICFBamEnumTagByEnumNameIdxKey keyEnumNameIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
				keyEnumNameIdx.setRequiredEnumId( keepObj.getRequiredEnumId() );
				keyEnumNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByEnumNameIdx.put( keyEnumNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamEnumTagByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamEnumTagByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamEnumTagObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamEnumTagObj createEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = Obj;
		ICFBamEnumTag rec = obj.getEnumTagRec();
		schema.getCFBamBackingStore().getTableEnumTag().createEnumTag(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		ICFBamEnumTagObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamEnumTagObj readEnumTag( CFLibDbKeyHash256 pkey ) {
		return( readEnumTag( pkey, false ) );
	}

	@Override
	public ICFBamEnumTagObj readEnumTag( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamEnumTagObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamEnumTag readRec = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamEnumTagObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamEnumTagObj readCachedEnumTag( CFLibDbKeyHash256 pkey ) {
		ICFBamEnumTagObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeEnumTag( ICFBamEnumTagObj obj )
	{
		final String S_ProcName = "CFBamEnumTagTableObj.reallyDeepDisposeEnumTag() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamEnumTagObj existing = readCachedEnumTag( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamEnumTagByEnumIdxKey keyEnumIdx = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
		keyEnumIdx.setRequiredEnumId( existing.getRequiredEnumId() );

		ICFBamEnumTagByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamEnumTagByEnumNameIdxKey keyEnumNameIdx = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
		keyEnumNameIdx.setRequiredEnumId( existing.getRequiredEnumId() );
		keyEnumNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamEnumTagByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamEnumTagByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );



		if( indexByEnumIdx != null ) {
			if( indexByEnumIdx.containsKey( keyEnumIdx ) ) {
				indexByEnumIdx.get( keyEnumIdx ).remove( pkey );
				if( indexByEnumIdx.get( keyEnumIdx ).size() <= 0 ) {
					indexByEnumIdx.remove( keyEnumIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByEnumNameIdx != null ) {
			indexByEnumNameIdx.remove( keyEnumNameIdx );
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeEnumTag( CFLibDbKeyHash256 pkey ) {
		ICFBamEnumTagObj obj = readCachedEnumTag( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamEnumTagObj lockEnumTag( CFLibDbKeyHash256 pkey ) {
		ICFBamEnumTagObj locked = null;
		ICFBamEnumTag lockRec = schema.getCFBamBackingStore().getTableEnumTag().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getEnumTagTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamEnumTagObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockEnumTag", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamEnumTagObj> readAllEnumTag() {
		return( readAllEnumTag( false ) );
	}

	@Override
	public List<ICFBamEnumTagObj> readAllEnumTag( boolean forceRead ) {
		final String S_ProcName = "readAllEnumTag";
		if( ( allEnumTag == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> map = new HashMap<CFLibDbKeyHash256,ICFBamEnumTagObj>();
			allEnumTag = map;
			ICFBamEnumTag[] recList = schema.getCFBamBackingStore().getTableEnumTag().readAllDerived( null );
			ICFBamEnumTag rec;
			ICFBamEnumTagObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamEnumTagObj realised = (ICFBamEnumTagObj)obj.realise();
			}
		}
		int len = allEnumTag.size();
		ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
		Iterator<ICFBamEnumTagObj> valIter = allEnumTag.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamEnumTagObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamEnumTagObj> readCachedAllEnumTag() {
		final String S_ProcName = "readCachedAllEnumTag";
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>();
		if( allEnumTag != null ) {
			int len = allEnumTag.size();
			ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
			Iterator<ICFBamEnumTagObj> valIter = allEnumTag.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamEnumTagObj readEnumTagByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readEnumTagByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamEnumTagObj readEnumTagByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamEnumTagObj obj = readEnumTag( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId )
	{
		return( readEnumTagByEnumIdx( EnumId,
			false ) );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId,
		boolean forceRead )
	{
		final String S_ProcName = "readEnumTagByEnumIdx";
		ICFBamEnumTagByEnumIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
		key.setRequiredEnumId( EnumId );
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
		if( indexByEnumIdx == null ) {
			indexByEnumIdx = new HashMap< ICFBamEnumTagByEnumIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( ( ! forceRead ) && indexByEnumIdx.containsKey( key ) ) {
			dict = indexByEnumIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
			ICFBamEnumTagObj obj;
			ICFBamEnumTag[] recList = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByEnumIdx( null,
				EnumId );
			ICFBamEnumTag rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamEnumTagObj realised = (ICFBamEnumTagObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByEnumIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
		Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamEnumTagObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readEnumTagByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readEnumTagByDefSchemaIdx";
		ICFBamEnumTagByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamEnumTagByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
			ICFBamEnumTagObj obj;
			ICFBamEnumTag[] recList = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamEnumTag rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamEnumTagObj realised = (ICFBamEnumTagObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
		Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamEnumTagObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamEnumTagObj readEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name )
	{
		return( readEnumTagByEnumNameIdx( EnumId,
			Name,
			false ) );
	}

	@Override
	public ICFBamEnumTagObj readEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name, boolean forceRead )
	{
		if( indexByEnumNameIdx == null ) {
			indexByEnumNameIdx = new HashMap< ICFBamEnumTagByEnumNameIdxKey,
				ICFBamEnumTagObj >();
		}
		ICFBamEnumTagByEnumNameIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
		key.setRequiredEnumId( EnumId );
		key.setRequiredName( Name );
		ICFBamEnumTagObj obj = null;
		if( ( ! forceRead ) && indexByEnumNameIdx.containsKey( key ) ) {
			obj = indexByEnumNameIdx.get( key );
		}
		else {
			ICFBamEnumTag rec = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByEnumNameIdx( null,
				EnumId,
				Name );
			if( rec != null ) {
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamEnumTagObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readEnumTagByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readEnumTagByPrevIdx";
		ICFBamEnumTagByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamEnumTagByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
			ICFBamEnumTagObj obj;
			ICFBamEnumTag[] recList = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamEnumTag rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamEnumTagObj realised = (ICFBamEnumTagObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
		Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamEnumTagObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readEnumTagByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamEnumTagObj> readEnumTagByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readEnumTagByNextIdx";
		ICFBamEnumTagByNextIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamEnumTagByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamEnumTagObj>();
			ICFBamEnumTagObj obj;
			ICFBamEnumTag[] recList = schema.getCFBamBackingStore().getTableEnumTag().readDerivedByNextIdx( null,
				NextId );
			ICFBamEnumTag rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getEnumTagTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamEnumTagObj realised = (ICFBamEnumTagObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
		Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamEnumTagObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamEnumTagObj readCachedEnumTagByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamEnumTagObj obj = null;
		obj = readCachedEnumTag( Id );
		return( obj );
	}

	@Override
	public List<ICFBamEnumTagObj> readCachedEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId )
	{
		final String S_ProcName = "readCachedEnumTagByEnumIdx";
		ICFBamEnumTagByEnumIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
		key.setRequiredEnumId( EnumId );
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>();
		if( indexByEnumIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
			if( indexByEnumIdx.containsKey( key ) ) {
				dict = indexByEnumIdx.get( key );
				int len = dict.size();
				ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
				Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamEnumTagObj obj;
			Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamEnumTagObj> readCachedEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedEnumTagByDefSchemaIdx";
		ICFBamEnumTagByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
				Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamEnumTagObj obj;
			Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamEnumTagObj readCachedEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name )
	{
		ICFBamEnumTagObj obj = null;
		ICFBamEnumTagByEnumNameIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
		key.setRequiredEnumId( EnumId );
		key.setRequiredName( Name );
		if( indexByEnumNameIdx != null ) {
			if( indexByEnumNameIdx.containsKey( key ) ) {
				obj = indexByEnumNameIdx.get( key );
			}
			else {
				Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamEnumTagObj> readCachedEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedEnumTagByPrevIdx";
		ICFBamEnumTagByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
				Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamEnumTagObj obj;
			Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamEnumTagObj> readCachedEnumTagByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedEnumTagByNextIdx";
		ICFBamEnumTagByNextIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamEnumTagObj> arrayList = new ArrayList<ICFBamEnumTagObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamEnumTagObj arr[] = new ICFBamEnumTagObj[len];
				Iterator<ICFBamEnumTagObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamEnumTagObj obj;
			Iterator<ICFBamEnumTagObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamEnumTagObj> cmp = new Comparator<ICFBamEnumTagObj>() {
			@Override
			public int compare( ICFBamEnumTagObj lhs, ICFBamEnumTagObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeEnumTagByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamEnumTagObj obj = readCachedEnumTagByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId )
	{
		final String S_ProcName = "deepDisposeEnumTagByEnumIdx";
		ICFBamEnumTagObj obj;
		List<ICFBamEnumTagObj> arrayList = readCachedEnumTagByEnumIdx( EnumId );
		if( arrayList != null )  {
			Iterator<ICFBamEnumTagObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeEnumTagByDefSchemaIdx";
		ICFBamEnumTagObj obj;
		List<ICFBamEnumTagObj> arrayList = readCachedEnumTagByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamEnumTagObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name )
	{
		ICFBamEnumTagObj obj = readCachedEnumTagByEnumNameIdx( EnumId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeEnumTagByPrevIdx";
		ICFBamEnumTagObj obj;
		List<ICFBamEnumTagObj> arrayList = readCachedEnumTagByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamEnumTagObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeEnumTagByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeEnumTagByNextIdx";
		ICFBamEnumTagObj obj;
		List<ICFBamEnumTagObj> arrayList = readCachedEnumTagByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamEnumTagObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamEnumTagObj updateEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = Obj;
		schema.getCFBamBackingStore().getTableEnumTag().updateEnumTag( null,
			Obj.getEnumTagRec() );
		obj = (ICFBamEnumTagObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = Obj;
		ICFBamEnumTagObj prev = obj.getOptionalLookupPrev();
		ICFBamEnumTagObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTag( null,
			obj.getEnumTagRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteEnumTagByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamEnumTagObj obj = readEnumTag(Id);
		if( obj != null ) {
			ICFBamEnumTagEditObj editObj = (ICFBamEnumTagEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamEnumTagEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeEnumTagByIdIdx( Id );
	}

	@Override
	public void deleteEnumTagByEnumIdx( CFLibDbKeyHash256 EnumId )
	{
		ICFBamEnumTagByEnumIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumIdxKey();
		key.setRequiredEnumId( EnumId );
		if( indexByEnumIdx == null ) {
			indexByEnumIdx = new HashMap< ICFBamEnumTagByEnumIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( indexByEnumIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict = indexByEnumIdx.get( key );
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByEnumIdx( null,
				EnumId );
			Iterator<ICFBamEnumTagObj> iter = dict.values().iterator();
			ICFBamEnumTagObj obj;
			List<ICFBamEnumTagObj> toForget = new LinkedList<ICFBamEnumTagObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByEnumIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByEnumIdx( null,
				EnumId );
		}
		deepDisposeEnumTagByEnumIdx( EnumId );
	}

	@Override
	public void deleteEnumTagByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamEnumTagByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamEnumTagByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamEnumTagObj> iter = dict.values().iterator();
			ICFBamEnumTagObj obj;
			List<ICFBamEnumTagObj> toForget = new LinkedList<ICFBamEnumTagObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeEnumTagByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteEnumTagByEnumNameIdx( CFLibDbKeyHash256 EnumId,
		String Name )
	{
		if( indexByEnumNameIdx == null ) {
			indexByEnumNameIdx = new HashMap< ICFBamEnumTagByEnumNameIdxKey,
				ICFBamEnumTagObj >();
		}
		ICFBamEnumTagByEnumNameIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByEnumNameIdxKey();
		key.setRequiredEnumId( EnumId );
		key.setRequiredName( Name );
		ICFBamEnumTagObj obj = null;
		if( indexByEnumNameIdx.containsKey( key ) ) {
			obj = indexByEnumNameIdx.get( key );
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByEnumNameIdx( null,
				EnumId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByEnumNameIdx( null,
				EnumId,
				Name );
		}
		deepDisposeEnumTagByEnumNameIdx( EnumId,
				Name );
	}

	@Override
	public void deleteEnumTagByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamEnumTagByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamEnumTagByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByPrevIdx( null,
				PrevId );
			Iterator<ICFBamEnumTagObj> iter = dict.values().iterator();
			ICFBamEnumTagObj obj;
			List<ICFBamEnumTagObj> toForget = new LinkedList<ICFBamEnumTagObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByPrevIdx( null,
				PrevId );
		}
		deepDisposeEnumTagByPrevIdx( PrevId );
	}

	@Override
	public void deleteEnumTagByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamEnumTagByNextIdxKey key = schema.getCFBamBackingStore().getFactoryEnumTag().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamEnumTagByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamEnumTagObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamEnumTagObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByNextIdx( null,
				NextId );
			Iterator<ICFBamEnumTagObj> iter = dict.values().iterator();
			ICFBamEnumTagObj obj;
			List<ICFBamEnumTagObj> toForget = new LinkedList<ICFBamEnumTagObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableEnumTag().deleteEnumTagByNextIdx( null,
				NextId );
		}
		deepDisposeEnumTagByNextIdx( NextId );
	}

	/**
	 *	Move the CFBamEnumTagObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamEnumTagObj refreshed cache instance.
	 */
	@Override
	public ICFBamEnumTagObj moveUpEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpEnumTag" );
		}
		ICFBamEnumTag rec = schema.getCFBamBackingStore().getTableEnumTag().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getEnumTagTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamEnumTagObj)obj.realise();
			ICFBamEnumTagObj prev = obj.getOptionalLookupPrev( true );
			ICFBamEnumTagObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamEnumTagObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamEnumTagObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamEnumTagObj refreshed cache instance.
	 */
	@Override
	public ICFBamEnumTagObj moveDownEnumTag( ICFBamEnumTagObj Obj ) {
		ICFBamEnumTagObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownEnumTag" );
		}
		ICFBamEnumTag rec = schema.getCFBamBackingStore().getTableEnumTag().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getEnumTagTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamEnumTagObj)obj.realise();
			ICFBamEnumTagObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamEnumTagObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamEnumTagObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}